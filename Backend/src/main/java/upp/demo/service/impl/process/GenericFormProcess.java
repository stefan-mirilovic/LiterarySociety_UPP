package upp.demo.service.impl.process;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.feel.syntaxtree.For;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormDto;

import upp.demo.dto.FormSubmissionDto;
import upp.demo.globals.PropertyName;
import upp.demo.helper.FormFieldsHelper;
import upp.demo.service.ProcessInstanceService;
import upp.demo.globals.Processes.*;
import upp.demo.service.ProcessService;
import upp.demo.service.TaskService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenericFormProcess implements ProcessInstanceService {

	private final TaskService taskService;
	private final FormService formService;
	private final ProcessService processService;
	private final RuntimeService runtimeService;
	private final FormFieldsHelper formFieldsHelper;

	@Override
	public FormDto startProcess(String processName) {
		ProcessInstance processInstance = processService.start(processName);
		String processInstanceId = processInstance.getId();

		Task task = taskService.getByProcess(processInstanceId);
		String taskId = task.getId();

		FormDto formDto = new FormDto();
		formDto.setTaskId(taskId);
		formDto.setProcessInstanceId(processInstanceId);
		return formDto;
	}

	@Override
	public FormDto getFormFields(String taskId) {
		Task task = taskService.getById(taskId);
		TaskFormData taskFormData = taskService.formData(taskId);

		FormDto formDto = new FormDto();
		formDto.setTaskId(taskId);
		formDto.setProcessInstanceId(task.getProcessInstanceId());
		formDto.setFormFields(formFieldsHelper.convertToDto(task.getProcessInstanceId(),taskFormData.getFormFields()));
		return formDto;
	}

	@Override
	public String submitForm(String taskId, List<FormSubmissionDto> submissionDto) {
		Task task = taskService.getById(taskId);
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, PropertyName.FormName.FORM_DATA,submissionDto);
		formService.submitTaskForm(taskId,formFieldsHelper.listToMapSubmit(submissionDto));
		return processInstanceId;
	}


	@Override
	public List<TaskDto> findNextTasks(String processId) {
		return null;
	}

	@Override
	public List<TaskDto> getAllTasks(String name) {
		return null;
	}
}
