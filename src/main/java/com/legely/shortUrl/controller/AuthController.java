package com.legely.shortUrl.controller;

import com.legely.shortUrl.dto.RegisterRequest;
import com.legely.shortUrl.dto.UserResponse;
import com.legely.shortUrl.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public UserResponse registerUser(@RequestBody RegisterRequest registerRequest) {
        return authService.registration(registerRequest);
    }
}
