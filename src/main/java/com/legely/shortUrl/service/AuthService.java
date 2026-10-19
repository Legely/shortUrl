package com.legely.shortUrl.service;

import com.legely.shortUrl.dto.*;
import com.legely.shortUrl.entity.ShortUrl;
import com.legely.shortUrl.entity.User;
import com.legely.shortUrl.repository.ShortUrlRepository;
import com.legely.shortUrl.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder  passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // регистрация
    @Transactional
    public UserResponse registration(RegisterRequest request) {


        if (userRepository.existsByNickname(request.nickname())) {
            throw new RuntimeException("Пользователь с таким nickname уже существует!");
        }

        User user = new User(request.nickname(),  passwordEncoder.encode(request.password()));
        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getNickname()
        );

    }


    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByNickname(request.nickname())
                .orElseThrow(() -> new RuntimeException("Неверный email или пароль"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Неверный email или пароль");
        }
        else{
            String token = jwtService.generateJwtToken(user.getNickname());

            return new AuthResponse(token, user.getNickname());
        }
    }
}
