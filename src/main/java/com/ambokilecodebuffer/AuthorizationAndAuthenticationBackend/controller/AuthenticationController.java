package com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.controller;

import com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @CrossOrigin(origins = "http://0.0.0.0:*")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        log.info("registration operation process performed inside register method of AuthenticationController");
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @CrossOrigin(origins = "http://0.0.0.0:*")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        log.info("SignUp operation process performed inside login method of AuthenticationController");
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
}
