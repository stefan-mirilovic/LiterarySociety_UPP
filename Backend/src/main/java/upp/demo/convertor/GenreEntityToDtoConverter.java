package upp.demo.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import upp.demo.dto.EnumDto;
import upp.demo.dto.GenreDto;
import upp.demo.model.Genre;

@Component
public class GenreEntityToDtoConverter implements Converter<Genre, EnumDto> {
	@Override
	public EnumDto convert(Genre genre) {
		EnumDto genreDto = new EnumDto();
		genreDto.setId(genre.getId());
		genreDto.setValue(genre.getName());
		return genreDto;
	}
}
