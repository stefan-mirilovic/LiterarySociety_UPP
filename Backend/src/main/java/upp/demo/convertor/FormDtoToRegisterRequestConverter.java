package upp.demo.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.model.RegisterReaderRequest;

import java.util.List;

@Component
public class FormDtoToRegisterRequestConverter implements Converter<List<FormSubmissionDto>, RegisterReaderRequest> {
	@Override
	public RegisterReaderRequest convert(List<FormSubmissionDto> formSubmissionDtos) {
		RegisterReaderRequest registerReaderRequest = new RegisterReaderRequest();
		for(FormSubmissionDto form: formSubmissionDtos){
			if(form.getFieldId().equals("username")){
				registerReaderRequest.setUsername(form.getFieldValue());
			}
			if(form.getFieldId().equals("password")){
				registerReaderRequest.setPassword(form.getFieldValue());
			}
			if(form.getFieldId().equals("email")){
				registerReaderRequest.setEmail(form.getFieldValue());
			}
			if(form.getFieldId().equals("name")){
				registerReaderRequest.setName(form.getFieldValue());
			}
			if(form.getFieldId().equals("surname")){
				registerReaderRequest.setSurname(form.getFieldValue());
			}
			if(form.getFieldId().equals("city")){
				registerReaderRequest.setCity(form.getFieldValue());
			}
			if(form.getFieldId().equals("country")){
				registerReaderRequest.setCountry(form.getFieldValue());
			}
			if(form.getFieldId().equals("role")){
				if(form.getFieldValue().equals("BetaReader"))
					registerReaderRequest.setBeta(true);
				else
					registerReaderRequest.setBeta(false);
			}
		}
		return registerReaderRequest;
	}
}
