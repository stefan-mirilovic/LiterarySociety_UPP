package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upp.demo.dto.OrderDTO;
import upp.demo.dto.TransactionDTO;
import upp.demo.enumeration.TransactionStatus;
import upp.demo.service.impl.TransactionService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody TransactionDTO dto){
        try {
            OrderDTO result = transactionService.create(dto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "{id}/success/{code}")
    public ResponseEntity<?> success(@PathVariable Long id, @PathVariable String code) {
        try {
            TransactionDTO result = transactionService.changeStatus(id, code, TransactionStatus.COMPLETED);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}/failed/{code}")
    public ResponseEntity<?> failed(@PathVariable Long id, @PathVariable String code) {
        try {
            TransactionDTO result = transactionService.changeStatus(id, code, TransactionStatus.FAILED);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}/error/{code}")
    public ResponseEntity<?> error(@PathVariable Long id, @PathVariable String code) {
        try {
            TransactionDTO result = transactionService.changeStatus(id, code, TransactionStatus.ERROR);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
