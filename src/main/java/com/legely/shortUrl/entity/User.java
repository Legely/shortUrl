package com.legely.shortUrl.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    // Здесь хранится BCrypt-хеш, а не оригинальный пароль
    @Column(nullable = false)
    private String password;

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }
    public String getNickname() {
        return nickname;
    }
    public String getPassword() {
        return password;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
