package com.legely.shortUrl.dto;

import java.time.LocalDateTime;

public record ShortUrlStatsResponse (
        String shortCode,
        String originalUrl,
        Long clickCount,
        LocalDateTime createdAt ) {}
