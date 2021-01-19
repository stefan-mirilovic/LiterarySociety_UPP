package upp.demo.service.task;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.globals.PropertyName;
import upp.demo.helper.ValidationHelper;
import upp.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataValidationService implements JavaDelegate {

	private final UserRepository userRepository;
	private final ValidationHelper validationHelper;

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		boolean isValid = false;
		List<FormSubmissionDto> formData = (List<FormSubmissionDto>) delegateExecution.getVariable(PropertyName.FormName.FORM_DATA);
		String email = validationHelper.getValidation(formData);
		if (userRepository.findByEmail(email) == null) {
			isValid = true;
		}
		delegateExecution.setVariable(PropertyName.VariableName.VALID, isValid);
	}
}
