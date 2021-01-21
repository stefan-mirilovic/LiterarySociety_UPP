package upp.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.model.RegisterReaderRequest;
import upp.demo.service.impl.CustomUserDetailsService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegisterReaderMapper {

    private final CustomUserDetailsService userDetailsService;

    public RegisterReaderRequest convert(List<FormSubmissionDto> submissionForm) {
        RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest();
        for (FormSubmissionDto form : submissionForm) {
            if (form.getFieldId().equals("username")) {
                registerReaderRequest.setUsername(form.getFieldValue());
            }
            if (form.getFieldId().equals("password")) {
                registerReaderRequest.setPassword(userDetailsService.encodePassword(form.getFieldValue()));
            }
            if (form.getFieldId().equals("email")) {
                registerReaderRequest.setEmail(form.getFieldValue());
            }
            if (form.getFieldId().equals("name")) {
                registerReaderRequest.setName(form.getFieldValue());
            }
            if (form.getFieldId().equals("surname")) {
                registerReaderRequest.setSurname(form.getFieldValue());
            }
            if (form.getFieldId().equals("city")) {
                registerReaderRequest.setCity(form.getFieldValue());
            }
            if (form.getFieldId().equals("country")) {
                registerReaderRequest.setCountry(form.getFieldValue());
            }
            if (form.getFieldId().equals("role")) {
                if (form.getFieldValue().equals("BetaReader"))
                    registerReaderRequest.setBeta(true);
                else
                    registerReaderRequest.setBeta(false);
            }
        }
        return registerReaderRequest;
    }
}
