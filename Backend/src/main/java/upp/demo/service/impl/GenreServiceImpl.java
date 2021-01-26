package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import upp.demo.dto.GenreDto;
import upp.demo.mapper.GenreMapper;
import upp.demo.model.Genre;
import upp.demo.repository.GenreRepository;
import upp.demo.service.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

	private final GenreRepository genreRepository;
	private final ConversionService conversionService;

	@Override
	public List<GenreDto> getAllGenres() {
		List<Genre> genres = genreRepository.findAll();
		//return genres.stream().map(x -> conversionService.convert(x, GenreDto.class)).collect(Collectors.toList());
		List<GenreDto> results = new ArrayList<>();
		for (Genre genre: genres) {
			results.add(new GenreDto(genre.getId(), genre.getName()));
		}
		return results;
	}
}

