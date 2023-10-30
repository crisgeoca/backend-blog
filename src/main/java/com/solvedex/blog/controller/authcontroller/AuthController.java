package com.solvedex.blog.controller.authcontroller;

import com.solvedex.blog.domain.User;
import com.solvedex.blog.dto.AuthCredentialsDto;
import com.solvedex.blog.service.interfaces.AuthInterface;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthInterface authInterface;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthCredentialsDto authCredentialsDto) {
        try {
            User user = authInterface.getUser(authCredentialsDto.getUsername(), authCredentialsDto.getPassword());
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            authInterface.getToken(user))
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String token, @AuthenticationPrincipal User user) {
        try {
            return ResponseEntity.ok(authInterface.validateTokenExpiration(token, user));

        } catch (ExpiredJwtException e) {
            return ResponseEntity.ok(false);
        }
    }

}
