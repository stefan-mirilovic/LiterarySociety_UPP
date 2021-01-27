package upp.demo.model;

import lombok.Data;
import upp.demo.enumeration.DocumentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Book implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String ownerEmail;

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

	@Column
	private String synopsis;

	@ManyToOne
	private Genre genre;

	@Column
	private Boolean published;

	@Column
	private DocumentStatus documentStatus;

	@Column
	private String documentPath;
}
