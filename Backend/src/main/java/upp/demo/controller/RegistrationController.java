package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upp.demo.dto.FormSubmissionDto;
import upp.demo.dto.RegistrationFormDto;
import upp.demo.service.RegistrationService;
import upp.demo.service.impl.RegisterReaderRequestService;

import java.util.UUID;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationController {

	private final RegistrationService registrationService;
	private final RegisterReaderRequestService registerReaderRequestService;

	@GetMapping("/render/form")
	public ResponseEntity<RegistrationFormDto>  renderForm() {
        return new ResponseEntity<>(registrationService.getForm(), HttpStatus.OK);
	}

	@PostMapping("/register/reader/{taskId}")
	public void registerReader(@RequestBody List<FormSubmissionDto> formSubmissionList, @PathVariable("taskId") String taskId){
		registrationService.createRegisterRequest(formSubmissionList, taskId);
	}

	@GetMapping("approve/{id}/{approveCode}")
	public ResponseEntity<?> approve(@PathVariable Long id, @PathVariable UUID approveCode) {
		return new ResponseEntity<String>(registerReaderRequestService.approve(id, approveCode), HttpStatus.OK);
	}
}
