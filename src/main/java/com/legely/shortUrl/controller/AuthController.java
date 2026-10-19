package com.legely.shortUrl.controller;

import com.legely.shortUrl.dto.AuthResponse;
import com.legely.shortUrl.dto.LoginRequest;
import com.legely.shortUrl.dto.RegisterRequest;
import com.legely.shortUrl.dto.UserResponse;
import com.legely.shortUrl.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private   final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody RegisterRequest request) {
        return authService.registration(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
