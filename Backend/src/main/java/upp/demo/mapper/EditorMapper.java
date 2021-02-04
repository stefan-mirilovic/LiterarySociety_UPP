package upp.demo.mapper;

import org.springframework.stereotype.Component;
import upp.demo.dto.EnumDto;
import upp.demo.model.Genre;
import upp.demo.model.User;

@Component
public class EditorMapper {
    public EnumDto convert(User editor) {
        EnumDto genreDto = new EnumDto();
        genreDto.setId(editor.getId());
        genreDto.setValue(editor.getEmail());
        return genreDto;
    }
}
