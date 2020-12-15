package upp.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Keywords {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String words;
}
