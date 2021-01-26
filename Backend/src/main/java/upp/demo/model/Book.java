package upp.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User owner;

	@ManyToMany
	private List<User> editors = new ArrayList<>();

	@ManyToMany
	private List<User> betaReaders = new ArrayList<>();

	@Column
	private String isbn;

	@Column
	private String title;

	@Column
	private int publishingYear;

	@Column
	private int noOfPages;

	@Column(columnDefinition="text")
	private String synopsis;

	@ManyToOne
	private Genre genre;

	@Column
	private Boolean published;
}
