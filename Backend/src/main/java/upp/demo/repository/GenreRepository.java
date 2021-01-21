package upp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upp.demo.model.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
	List<Genre> findAllByNameIn(List<String> genres);
	Genre findByName(String name);
}
