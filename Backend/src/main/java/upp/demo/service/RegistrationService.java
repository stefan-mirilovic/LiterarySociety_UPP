package upp.demo.service;

import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.RegistrationFormDto;

import java.util.List;

public interface RegistrationService {
	/**
	 * Method for rendering custom form from camunda process engine
	 * @return RegistrationFormDto
	 */
	RegistrationFormDto getForm();

	void createRegisterRequest(List<FormSubmissionDto> formSubmissionDtoList, String taskId);
}
