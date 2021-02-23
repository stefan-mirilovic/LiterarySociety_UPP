package upp.demo.mapper;

import org.springframework.stereotype.Component;
import upp.demo.dto.EnumDto;
import upp.demo.elastic.model.BetaIndex;
import upp.demo.model.Genre;
import upp.demo.model.User;

@Component
public class EditorMapper {
    public EnumDto convert(User user) {
        EnumDto genreDto = new EnumDto();
        genreDto.setId(user.getId());
        genreDto.setValue(user.getEmail());
        return genreDto;
    }
}
