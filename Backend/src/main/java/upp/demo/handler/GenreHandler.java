package upp.demo.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import upp.demo.model.Genre;
import upp.demo.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreHandler {
	private final GenreRepository genreRepository;

	public List<String> getGenres() {
		List<String> genres = new ArrayList<>();
		List<Genre> genreList = genreRepository.findAll();
		for (Genre genre : genreList) {
			genres.add(genre.getName());
		}
		return genres;
	}
}
