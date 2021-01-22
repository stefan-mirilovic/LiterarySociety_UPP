package upp.demo.service.impl.process;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.TableDto;
import upp.demo.dto.TaskDto;
import upp.demo.service.ProcessInstanceService;
import upp.demo.service.TaskService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GenericTableProcess  {

	private final TaskService taskService;

	public FormDto startProcess(String processName) {
		return null;
	}

	public FormDto getFormFields(String taskId) {
		return null;
	}

	public String submitForm(String taskId, List<FormSubmissionDto> submissionDto) {
		return null;
	}

	public TableDto findNextTasks(String processId) {
		List<Task> tasks = taskService.getAllByProcess(processId);
		Task currentTask = tasks.get(0);
		TableDto tableDto = new TableDto();
		tableDto.setTaskId(currentTask.getId());
		tableDto.setProcessInstanceId(processId);
		tableDto.setTableFields();
		return
	}

	public List<TaskDto> getAllTasks(String name) {
		return null;
	}


}
