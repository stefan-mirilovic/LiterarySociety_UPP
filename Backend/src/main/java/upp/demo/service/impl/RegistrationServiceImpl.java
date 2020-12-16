package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.RegistrationFormDto;
import upp.demo.service.RegistrationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final RuntimeService runtimeService;

	private final TaskService taskService;

	private final FormService formService;

	@Override
	public RegistrationFormDto getForm() {
		RegistrationFormDto registrationForm = new RegistrationFormDto();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_14uovfx");

		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);

		TaskFormData tfd = formService.getTaskFormData(task.getId());

		List<FormField> properties = tfd.getFormFields();
		registrationForm.setFormFields(properties);
		registrationForm.setProcessInstanceId(processInstance.getId());
		registrationForm.setTaskId(task.getId());
		return registrationForm;
	}

	@Override
	public void createRegisterRequest(List<FormSubmissionDto> formSubmissionDtoList, String taskId) {
		
	}
}
