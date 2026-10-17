package com.legely.shortUrl.repository;

import com.legely.shortUrl.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    Optional<ShortUrl> findByShortCode(String shortCode);

    void deleteByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);

}
