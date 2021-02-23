package upp.demo.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import upp.demo.elastic.model.BetaIndex;

public interface BetaIndexRepository extends ElasticsearchRepository<BetaIndex, Long> {
}
