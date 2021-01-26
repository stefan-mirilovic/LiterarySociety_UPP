package upp.demo.service;

import upp.demo.dto.GenreDto;

import java.util.List;

public interface GenreService {

	/**
	 * Method for returning all genres
	 * @return List of Genre Dto
	 */
	List<GenreDto> getAllGenres();

    List<GenreDto> findAllSortedByName();
}
