package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;
import upp.demo.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	private final org.camunda.bpm.engine.TaskService taskService;
	private final FormService formService;
	@Override
	public Task claim(String taskId, String userId) {
		return null;
	}

	@Override
	public Task getByProcess(String processInstanceId) {
		return taskService.createTaskQuery().processInstanceId(processInstanceId).list().get(0);
	}

	@Override
	public TaskFormData formData(String taskId) {
		return formService.getTaskFormData(taskId);
	}

	@Override
	public Task getById(String taskId) {
		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}

	@Override
	public List<Task> getAllByProcess(String processInstanceId) {
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();

		if (!tasks.isEmpty()) {
			final Task nextTask = tasks.iterator().next();
		}
		return tasks;
	}

	@Override
	public List<Task> getAllByUsername(String username) {
		return null;
	}
}
