package com.racediary.racediary.controller;

import com.racediary.racediary.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    record LoginRequest(String username, String password) {}
    record LoginResponse(String token) {}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {

        // Itt most fix user — később cserélhető DB-re
        if (!req.username().equals("user") || !req.password().equals("pass")) {
            throw new RuntimeException("Bad credentials");
        }

        return new LoginResponse(jwtUtil.generateToken(req.username()));
    }
}