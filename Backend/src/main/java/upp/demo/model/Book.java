package upp.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String isbn;

	@Column
	private String title;

	@Column
	private int publishingYear;

	@Column
	private int noOfPages;

	@Column
	private String synopsis;

}
