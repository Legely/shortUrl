package com.legely.shortUrl.controller;


import com.legely.shortUrl.dto.ShortUrlRequest;
import com.legely.shortUrl.dto.ShortUrlResponse;
import com.legely.shortUrl.dto.ShortUrlStatsResponse;
import com.legely.shortUrl.service.ShortUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ShortUrlController {
    private ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("/api/v1/urls")
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrlResponse createShortUrl(@RequestBody ShortUrlRequest request) {
        return shortUrlService.createShortUrl(request);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {
        String originalUrl = shortUrlService.gotoOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND) // Код 302
                .location(URI.create(originalUrl))     // Заголовок Location с исходным сайтом
                .build();
    }

    @GetMapping("/api/v1/urls/{shortCode}/stats")
    public ShortUrlStatsResponse statsShortUrl(@PathVariable String shortCode) {
        return shortUrlService.statsShortUrl(shortCode);
    }
}
