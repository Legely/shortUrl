package com.legely.shortUrl.repository;

import com.legely.shortUrl.entity.ShortUrl;
import com.legely.shortUrl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname(String nickname);

    Optional<User> findByNickname(String nickname);
}
