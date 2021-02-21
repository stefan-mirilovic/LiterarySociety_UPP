package upp.demo.elastic.utils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import upp.demo.elastic.model.BookIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultMapper implements SearchResultMapper {

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
        List<BookIndex> bookUnits = new ArrayList<>();

        for (SearchHit hit : searchResponse.getHits()) {
            if (searchResponse.getHits().getHits().length <= 0) {
                return null;
            }

            Map<String, Object> source = hit.getSourceAsMap();
            String highlight = "";

            try {
                highlight = "..." + hit.getHighlightFields().get("text").fragments()[0].toString() + "...";
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            BookIndex searchResult = BookIndex.builder()
                    .id(((Integer) source.get("id")).longValue())
                    .title((String) source.get("title"))
                    .openAccess((Boolean) source.get("openAccess"))
                    .basicInfo((String) source.get("basicInfo"))
                    .text(highlight)
                    .build();
            bookUnits.add(searchResult);
        }

        if (bookUnits.size() > 0) {
            return new AggregatedPageImpl(bookUnits, pageable, searchResponse.getHits().getTotalHits(),
                    searchResponse.getAggregations(), searchResponse.getScrollId(),
                    searchResponse.getHits().getMaxScore());
        }
        return null;
    }
}
