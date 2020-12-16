package upp.demo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upp.demo.dto.RegistrationFormDto;
import upp.demo.service.RegistrationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationController {

	private final RegistrationService registrationService;

	@GetMapping("/render/form")
	public ResponseEntity<RegistrationFormDto>  renderForm() {
        return new ResponseEntity<>(registrationService.getForm(), HttpStatus.OK);
	}
}
