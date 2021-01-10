package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upp.demo.dto.FormDto;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.service.impl.RegisterReaderRequestService;
import upp.demo.service.impl.process.RegistrationProcess;

import java.util.UUID;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationController {

	private final RegistrationProcess registrationProcess;
	private final RegisterReaderRequestService registerReaderRequestService;

	@GetMapping("/readerRegistration")
	public ResponseEntity<FormDto> registrationStart(){
		return new ResponseEntity<>(registrationProcess.startProcess(),HttpStatus.OK);
	}

	@GetMapping("/readerRegistration/{taskId}")
	public ResponseEntity<FormDto>  renderForm(@PathVariable String taskId) {
        return new ResponseEntity<>(registrationProcess.getFormFields(taskId), HttpStatus.OK);
	}

	@PostMapping("/register/reader/{taskId}")
	public void registerReader(@RequestBody List<FormSubmissionDto> formSubmissionList, @PathVariable("taskId") String taskId){
//		registrationService.createRegisterRequest(formSubmissionList, taskId);

	}

	@GetMapping("approve/{id}/{approveCode}")
	public ResponseEntity<?> approve(@PathVariable Long id, @PathVariable UUID approveCode) {
		return new ResponseEntity<String>(registerReaderRequestService.approve(id, approveCode), HttpStatus.OK);
	}
}
