package com.legely.shortUrl.service;

import com.legely.shortUrl.entity.ShortUrl;
import com.legely.shortUrl.dto.*;
import com.legely.shortUrl.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class ShortUrlService {

    String MAIN_URL = "http://localhost:8080/";
    private final ShortUrlRepository shortUrlRepository;


    // Алфавит для Base62 (буквы и цифры)
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private final SecureRandom random = new SecureRandom();




    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }


    // создание ссылки
    @Transactional
    public ShortUrlResponse createShortUrl(ShortUrlRequest request) {
        String shortCode = generateUniqueShortCode();

        ShortUrl shortUrl = new ShortUrl(request.originalUrl(),shortCode);
        ShortUrl savedUrl = shortUrlRepository.save(shortUrl);

        String fullUrl = MAIN_URL + savedUrl.getShortCode();

        return new ShortUrlResponse(
                savedUrl.getShortCode(),
                fullUrl,
                savedUrl.getOriginalUrl(),
                savedUrl.getCreatedAt()
        );

    }

    // метод перехода
    @Transactional
    public String gotoOriginalUrl(String shortCode) {
        ShortUrl shortUrl = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Ссылка с кодом " + shortCode + " не найдена"));


        shortUrl.incrementClickCount();

        return shortUrl.getOriginalUrl();
    }

    // метод получения статы
    @Transactional(readOnly = true)
    public ShortUrlStatsResponse statsShortUrl(String shortCode) {

        ShortUrl shortUrl = shortUrlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Ссылка с кодом " + shortCode + " не найдена"));

        return new ShortUrlStatsResponse(
                shortUrl.getShortCode(),
                shortUrl.getOriginalUrl(),
                shortUrl.getClickCount(),
                shortUrl.getCreatedAt()
        );

    }
    // метод получения статы
    @Transactional(readOnly = true)
    public List<ShortUrlResponse> findAll() {
        return shortUrlRepository.findAll()
                .stream()
                .map(url -> new ShortUrlResponse(
                        url.getShortCode(),
                        MAIN_URL + url.getShortCode(),
                        url.getOriginalUrl(),
                        url.getCreatedAt()
                ))
                .toList();
    }





    // Вспомогательный метод: генерирует код и гарантирует отсутствие коллизий в БД
    private String generateUniqueShortCode() {
        for (int attempt = 0; attempt < 30; attempt++) {
            String code = generateRandomString(CODE_LENGTH);
            if (!shortUrlRepository.existsByShortCode(code)) {
                return code;
            }
        }
        throw new IllegalStateException("Не удалось сгенерировать уникальный код ссылки");
    }

    // Генерация случайной строки фиксированной длины
    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }






}
