package upp.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Long id;

	@Column
	private String name;

	@ManyToMany
	private List<User> users;

	@ManyToMany(mappedBy = "genres")
	private List<RegisterReaderRequest> readerRequests;
}
