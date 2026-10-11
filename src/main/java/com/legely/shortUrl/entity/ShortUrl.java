package com.legely.shortUrl.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "shorturl")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String shortCode;


    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @Column(nullable = false)
    private Long clickCount = 0L;


    public ShortUrl(){}

    public ShortUrl(String originalUrl, String shortCode) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;


    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public String  getOriginalUrl() {
        return originalUrl;
    }
    public String getShortCode() {
        return shortCode;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public Long getClickCount() {
        return clickCount;
    }




    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public void incrementClickCount() {
        this.clickCount++;
    }
}


