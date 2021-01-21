package upp.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upp.demo.security.JwtAuthenticationRequest;
import upp.demo.security.UserTokenState;
import upp.demo.service.impl.AuthService;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                   HttpServletResponse response) {
        try {
            UserTokenState userToken = this.authService.createAuthenticationToken(authenticationRequest, response);
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
