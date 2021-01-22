package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upp.demo.dto.FormDto;
import upp.demo.service.impl.process.GenericTableProcess;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TableController {

	private final GenericTableProcess genericTableProcess;

	@GetMapping("/getTable/{id}")
	public ResponseEntity<?> getTableForm(@PathVariable("id") String processId){
		return new ResponseEntity<>(genericTableProcess.findNextTasks(processId), HttpStatus.OK);
	}

}
