package upp.demo.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import upp.demo.dto.GenreDto;
import upp.demo.model.Genre;

@Component
public class GenreEntityToDtoConverter implements Converter<Genre, GenreDto> {
	@Override
	public GenreDto convert(Genre genre) {
		GenreDto genreDto = new GenreDto();
		genreDto.setId(genre.getId());
		genreDto.setName(genre.getName());
		return genreDto;
	}
}
