package upp.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import upp.demo.enumeration.RegisterRequestStatus;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReaderRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @Column
    private String email;

    @Column
    private String username;

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
    private RegisterRequestStatus status;

    @Column
    private boolean isBeta;

    @Column
    private UUID approveCode;

    @ManyToMany
    @JoinTable(name = "register_request_genre", joinColumns = @JoinColumn(name = "genre_id"), inverseJoinColumns = @JoinColumn (name = "user_id"))
    private List<Genre> genres;
}
