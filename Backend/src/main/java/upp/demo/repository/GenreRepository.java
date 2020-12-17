package upp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
