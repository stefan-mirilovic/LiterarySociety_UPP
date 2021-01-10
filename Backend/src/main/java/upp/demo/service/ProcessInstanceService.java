package upp.demo.service;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import upp.demo.dto.FormDto;

import java.util.List;

public interface ProcessInstanceService {
	FormDto startProcess();

	FormDto getFormFields(String taskId);

	String submitForm();

	List<TaskDto> findNextTasks(String processId);

	List<TaskDto> getAllTasks(String name);
}
