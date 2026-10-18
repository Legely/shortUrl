package com.legely.shortUrl.service;

import com.legely.shortUrl.dto.RegisterRequest;
import com.legely.shortUrl.dto.ShortUrlRequest;
import com.legely.shortUrl.dto.UserResponse;
import com.legely.shortUrl.entity.User;
import com.legely.shortUrl.repository.ShortUrlRepository;
import com.legely.shortUrl.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder  passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
