package com.legely.shortUrl.service;

import com.legely.shortUrl.entity.ShortUrl;
import com.legely.shortUrl.dto.*;
import com.legely.shortUrl.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;

    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }


    // создание ссылки
    @Transactional
    public ShortUrlResponse createShortUrl(ShortUrlRequest shortUrlRequest) {
       ShortUrl shortUrl = new ShortUrl();

    }

    // метод перехода
    @Transactional(readOnly = true)
    public ShortUrlResponse gotoOriginalUrl(String shortCode) {

    }

    // метод получения статы
    @Transactional(readOnly = true)
    public ShortUrlResponse statsShortUrl(String shortCode) {

    }






}
