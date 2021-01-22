package upp.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import upp.demo.model.User;
import upp.demo.security.JwtAuthenticationRequest;
import upp.demo.security.TokenUtils;
import upp.demo.security.UserTokenState;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    public UserTokenState createAuthenticationToken(JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) throws Exception {
        final Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials. Please, try again");
        }
        User user = (User) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenUtils.generateToken(user.getUsername());
        Long expiresIn = tokenUtils.getExpiredIn();
        return new UserTokenState(jwt, expiresIn, user.getId(), user.getRole().toString(), false, user.getEmail());
    }
}
