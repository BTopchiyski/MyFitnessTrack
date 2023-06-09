package com.myfitnesstrack.myfitnesstrackapi.auth;

import com.myfitnesstrack.myfitnesstrackapi.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        if(service.userExists(request.getEmail())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(AuthenticationResponse.builder()
                            .error("User already exists")
                            .build());
        }
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        if(!service.userExists(request.getEmail())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(AuthenticationResponse.builder()
                            .error("Invalid email or password")
                            .build());
        }
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByToken(@RequestHeader("Authorization") String token) {
        User user = service.getUserByToken(token.substring(7));
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
