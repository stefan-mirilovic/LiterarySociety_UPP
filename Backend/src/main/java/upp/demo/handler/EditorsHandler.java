package upp.demo.handler;

import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import upp.demo.dto.EnumDto;
import upp.demo.enumeration.RoleEnum;
import upp.demo.mapper.EditorMapper;
import upp.demo.model.User;
import upp.demo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EditorsHandler {

    private final UserRepository userRepository;
    private final EditorMapper editorMapper;

    public List<EnumDto> getEditors() {
        List<User> editors = userRepository.findAllByRole(RoleEnum.EDITOR);
        return editors.stream().map(editorMapper::convert).collect(Collectors.toList());
    }
}
