package upp.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import upp.demo.dto.EnumDto;
import upp.demo.model.Genre;

@Component
@RequiredArgsConstructor
public class GenreMapper {
    public EnumDto convert(Genre genre) {
        EnumDto genreDto = new EnumDto();
        genreDto.setId(genre.getId());
        genreDto.setValue(genre.getName());
        return genreDto;
    }
}
