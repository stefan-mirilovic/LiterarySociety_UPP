package upp.demo.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import upp.demo.dto.EnumDto;
import upp.demo.dto.GenreDto;
import upp.demo.mapper.GenreMapper;
import upp.demo.model.Genre;
import upp.demo.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreHandler {
	private final GenreRepository genreRepository;
	private final GenreMapper genreMapper;

	public List<EnumDto> getGenres() {
		return genreRepository.findAll().stream().map(genreMapper::convert).collect(Collectors.toList());
	}


}
