package upp.demo.service.impl.process;

import upp.demo.dto.FormDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.TaskDto;
import upp.demo.service.ProcessInstanceService;

import java.util.List;

public class GenericTableProcess implements ProcessInstanceService {
	@Override
	public FormDto startProcess(String processName) {
		return null;
	}

	@Override
	public FormDto getFormFields(String taskId) {
		return null;
	}

	@Override
	public String submitForm(String taskId, List<FormSubmissionDto> submissionDto) {
		return null;
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
