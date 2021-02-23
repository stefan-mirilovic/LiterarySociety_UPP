package upp.demo.elastic.model;

import lombok.*;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(indexName = "literary-society-beta", replicas = 0, type = "beta")
public class BetaIndex {
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String email;
}
