package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upp.demo.dto.FormDto;
import upp.demo.dto.UserInterfaceDto;
import upp.demo.globals.PropertyName;
import upp.demo.service.impl.process.GenericFormProcess;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WriterController {
	private final GenericFormProcess genericFormProcess;

	@GetMapping("/writer/document/{id}")
	public ResponseEntity<UserInterfaceDto> getFormForDocument(@PathVariable ("id") String processId ) throws IOException {
		return new ResponseEntity<>(genericFormProcess.findNextTasks(processId), HttpStatus.OK);
	}
}
