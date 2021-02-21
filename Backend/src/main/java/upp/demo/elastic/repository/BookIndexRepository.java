package upp.demo.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import upp.demo.elastic.model.BookIndex;

public interface BookIndexRepository extends ElasticsearchRepository<BookIndex, Long> {
}
