package upp.demo.handler;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;
import upp.demo.dto.Coordinate;
import upp.demo.dto.EnumDto;
import upp.demo.elastic.model.BetaIndex;
import upp.demo.elastic.repository.BetaIndexRepository;
import upp.demo.elastic.service.GeolocationService;
import upp.demo.enumeration.RoleEnum;
import upp.demo.globals.PropertyName;
import upp.demo.mapper.BetaMapper;
import upp.demo.mapper.EditorMapper;
import upp.demo.model.User;
import upp.demo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BetaReadersHandler {


    private final UserRepository userRepository;
    private final BetaMapper betaMapper;
    private final RuntimeService runtimeService;
    private final GeolocationService geolocationService;
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final BetaIndexRepository betaIndexRepository;

    public List<EnumDto> getReaders(String processInstance) {
        String bookOwnerEmail = (String) runtimeService.getVariable(processInstance, PropertyName.BookPublishing.BOOK_OWNER_EMAIL);
        User writer = userRepository.findByEmail(bookOwnerEmail);
        Coordinate coordinate = geolocationService.getLocation(writer);

        GeoDistanceQueryBuilder geo = QueryBuilders
                .geoDistanceQuery("location")
                .point(coordinate.getLatitude(), coordinate.getLongitude())
                .distance(100, DistanceUnit.KILOMETERS);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.mustNot(geo);
        //*IMPLEMENT GENRES

        Iterable<BetaIndex> test = betaIndexRepository.findAll();
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build();
        List<BetaIndex> betaReaders = elasticsearchTemplate.queryForList(query, BetaIndex.class);

        return betaReaders.stream().map(betaMapper::convert).collect(Collectors.toList());
    }
}
