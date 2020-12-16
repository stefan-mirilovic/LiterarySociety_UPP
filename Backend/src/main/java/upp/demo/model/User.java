package upp.demo.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "user_table")
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private Long id;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String name;

	@Column
	private String surname;

	@Column
	private String city;

	@Column
	private String country;

	@Column
	private RoleEnum role;

	@ManyToMany
	@JoinTable(name = "user_genre", joinColumns = @JoinColumn(name = "genre_id"), inverseJoinColumns = @JoinColumn (name = "user_id"))
	private List<Genre> genres;
}
