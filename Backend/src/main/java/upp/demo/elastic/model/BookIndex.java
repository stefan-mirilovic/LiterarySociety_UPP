package upp.demo.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Data
@Document(indexName = "literary-society", type="book", replicas = 0)
public class BookIndex {

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String title;

    @Field(type = FieldType.Keyword)
    private String genre;

    @Field(type = FieldType.Boolean)
    private Boolean openAccess;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String writer;

    @Field(type = FieldType.Text)
    private String basicInfo;

    @Field(type = FieldType.Text, analyzer = "serbian-analyzer", searchAnalyzer = "serbian-analyzer")
    private String text;
}
