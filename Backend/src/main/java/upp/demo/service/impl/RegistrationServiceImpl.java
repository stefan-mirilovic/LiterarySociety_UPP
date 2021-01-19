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
import upp.demo.dto.FormDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.RegistrationFormDto;
import upp.demo.service.RegistrationService;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final RuntimeService runtimeService;

	private final TaskService taskService;

	private final FormService formService;

	@Override
	public FormDto startProcess() {
		return null;
	}

	@Override
	public RegistrationFormDto getForm() {
		RegistrationFormDto registrationForm = new RegistrationFormDto();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("readerRegistration");

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
		HashMap<String, Object> map = this.mapListToDto(formSubmissionDtoList);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "registration", formSubmissionDtoList);
		formService.submitTaskForm(taskId, map);
	}

	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDto temp : list){
			map.put(temp.getFieldId(), temp.getFieldValue());
		}

		return map;
	}
}
