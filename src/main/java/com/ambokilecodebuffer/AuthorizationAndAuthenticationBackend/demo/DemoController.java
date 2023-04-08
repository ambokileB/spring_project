package com.ambokilecodebuffer.AuthorizationAndAuthenticationBackend.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping(path = "/api/v1/auth/demo-controller")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello there this is productlist api");
    }

    @GetMapping(path = "/test/demo-controller")
    public ResponseEntity<String> sayHell() {
        return ResponseEntity.ok("Hello there this is productlist api");
    }
}
