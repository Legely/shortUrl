package com.legely.shortUrl.dto;



import java.time.LocalDateTime;

public record ShortUrlResponse (
        String shortCode,
        String shortUrl,
        String originalUrl,
        LocalDateTime createdAt ) {}
