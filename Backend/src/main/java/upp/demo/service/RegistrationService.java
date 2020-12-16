package upp.demo.service;

import upp.demo.dto.RegistrationFormDto;

public interface RegistrationService {
	/**
	 * Method for rendering custom form from camunda process engine
	 * @return RegistrationFormDto
	 */
	RegistrationFormDto getForm();
}
