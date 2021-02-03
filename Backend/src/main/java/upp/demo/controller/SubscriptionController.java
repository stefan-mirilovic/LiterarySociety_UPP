package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import upp.demo.dto.TransactionDTO;
import upp.demo.enumeration.SubscriptionStatus;
import upp.demo.enumeration.TransactionStatus;
import upp.demo.service.impl.SubscriptionService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping(value = "{id}/success/{code}")
    public RedirectView success(@PathVariable Long id, @PathVariable String code) throws Exception {
        String result = subscriptionService.changeStatus(id, code, SubscriptionStatus.ACTIVE);
        return new RedirectView(result);
    }

    @GetMapping(value = "{id}/failed/{code}")
    public ResponseEntity<?> failed(@PathVariable Long id, @PathVariable String code) {
        try {
            String result = subscriptionService.changeStatus(id, code, SubscriptionStatus.FAILED);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "{id}/error/{code}")
    public ResponseEntity<?> error(@PathVariable Long id, @PathVariable String code) {
        try {
            String result = subscriptionService.changeStatus(id, code, SubscriptionStatus.ERROR);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
