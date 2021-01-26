package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upp.demo.dto.BookStoreDisplayDTO;
import upp.demo.dto.GenreDto;
import upp.demo.service.GenreService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

	private final GenreService genreService;

	@GetMapping
	public ResponseEntity<?> allGenres() {
		return new ResponseEntity<>(genreService.getAllGenres(), HttpStatus.OK);
	}

	@GetMapping(value = "/store")
	public ResponseEntity<?> allGenresSorted() {
		try {
			List<GenreDto> result = genreService.findAllSortedByName();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
