package upp.demo.service;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import upp.demo.dto.FormDto;
import upp.demo.dto.FormSubmissionDto;

import java.util.List;

public interface ProcessInstanceService {
	FormDto startProcess(String processName);

	FormDto getFormFields(String taskId);

	String submitForm(String taskId, List<FormSubmissionDto> submissionDto);

	List<TaskDto> findNextTasks(String processId);

	List<TaskDto> getAllTasks(String name);
}
