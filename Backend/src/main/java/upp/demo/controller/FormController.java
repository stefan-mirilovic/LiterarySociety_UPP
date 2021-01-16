package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upp.demo.dto.FormDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.service.impl.RegisterReaderRequestService;
import upp.demo.service.impl.process.GenericFormProcess;

import java.util.UUID;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FormController {

	private final GenericFormProcess genericFormProcess;
	private final RegisterReaderRequestService registerReaderRequestService;

	@GetMapping("/startProcess/{processName}")
	public ResponseEntity<FormDto> processStart(@PathVariable("processName") String processName) {
		return new ResponseEntity<>(genericFormProcess.startProcess(processName), HttpStatus.OK);
	}

	@GetMapping("/getForm/{taskId}")
	public ResponseEntity<FormDto> renderForm(@PathVariable String taskId) {
		return new ResponseEntity<>(genericFormProcess.getFormFields(taskId), HttpStatus.OK);
	}

	@PostMapping("/submitForm/{taskId}")
	public void registerReader(@RequestBody List<FormSubmissionDto> formSubmissionList, @PathVariable("taskId") String taskId) {
		String processId = genericFormProcess.submitForm(taskId, formSubmissionList);
		List<TaskDto> tasks = genericFormProcess.findNextTasks(processId);
	}

	@GetMapping("approve/{id}/{approveCode}")
	public ResponseEntity<?> approve(@PathVariable Long id, @PathVariable UUID approveCode) {
		return new ResponseEntity<String>(registerReaderRequestService.approve(id, approveCode), HttpStatus.OK);
	}
}
