package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import upp.demo.dto.FormDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.TaskDto;
import upp.demo.globals.PropertyName;
import upp.demo.model.User;
import upp.demo.service.impl.RegisterReaderRequestService;
import upp.demo.service.impl.process.GenericFormProcess;

import javax.servlet.http.HttpSession;
import java.security.Principal;
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
		FormDto formDto = genericFormProcess.startProcess(processName);
		return new ResponseEntity<>(genericFormProcess.startProcess(processName), HttpStatus.OK);
	}

	@GetMapping("/getForm/{taskId}")
	public ResponseEntity<FormDto> renderForm(@PathVariable String taskId) {
		return new ResponseEntity<>(genericFormProcess.getFormFields(taskId), HttpStatus.OK);
	}

	@PostMapping("/submitForm/{taskId}")
	public void registerReader(@RequestBody List<FormSubmissionDto> formSubmissionList, @PathVariable("taskId") String taskId) {
		genericFormProcess.submitForm(taskId, formSubmissionList);
	}

	@GetMapping("approve/{id}/{approveCode}")
	public ResponseEntity<?> approve(@PathVariable Long id, @PathVariable UUID approveCode) {
		return new ResponseEntity<String>(registerReaderRequestService.approve(id, approveCode), HttpStatus.OK);
	}
}
