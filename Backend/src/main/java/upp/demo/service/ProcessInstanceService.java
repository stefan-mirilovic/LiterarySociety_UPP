package upp.demo.service;

import lombok.RequiredArgsConstructor;
import upp.demo.dto.FormDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.TaskDto;
import upp.demo.dto.UserInterfaceDto;

import java.io.IOException;
import java.util.List;

public interface ProcessInstanceService {
	FormDto startProcess(String processName);

	FormDto getFormFields(String taskId) throws Exception;

	String submitForm(String taskId, List<FormSubmissionDto> submissionDto) throws IOException;

	UserInterfaceDto findNextTasks(String processId) throws Exception;

	List<TaskDto> getAllTasks(String name);
}
