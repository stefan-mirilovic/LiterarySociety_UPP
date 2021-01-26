package upp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import upp.demo.enumeration.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Table(name = "user_table")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
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

	@Column
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Book> ownedBooks = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
