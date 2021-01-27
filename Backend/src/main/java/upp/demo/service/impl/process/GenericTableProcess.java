package upp.demo.service.impl.process;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import upp.demo.dto.FormDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.TableDto;
import upp.demo.dto.TaskDto;
import upp.demo.helper.TableHelper;
import upp.demo.service.ProcessInstanceService;
import upp.demo.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenericTableProcess {

	private final TaskService taskService;
	private final TableHelper tableHelper;

	public FormDto startProcess(String processName) {
		return null;
	}

	public TableDto getFormFields(String taskId){
		Task task = taskService.getById(taskId);
		TaskFormData taskFormData = taskService.formData(taskId);

		TableDto tableDto = new TableDto();
		tableDto.setProcessInstanceId(task.getProcessInstanceId());
		tableDto.setTaskId(taskId);
		tableDto.setTableFields(tableHelper.convertToBook(task.getProcessInstanceId(),taskFormData.getFormFields()));
		return tableDto;

	}

	public String submitForm(String taskId, List<FormSubmissionDto> submissionDto) {
		return null;
	}

	public TableDto findNextTasks(String processId) {
		List<Task> tasks = taskService.getAllByProcess(processId);
		Task currentTask = tasks.get(0);
		TaskFormData taskFormData = taskService.formData(currentTask.getId());

		TableDto tableDto = new TableDto();
		tableDto.setTableFields(tableHelper.convertToBook(processId, taskFormData.getFormFields()));
		tableDto.setTaskId(currentTask.getId());
		tableDto.setProcessInstanceId(processId);
		return tableDto;
	}

	public List<TaskDto> getAllTasks(String name) {
		return null;
	}


}
