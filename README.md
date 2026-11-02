# URL Shortener Service 🔗

REST API сервис для генерации компактных ссылок с системой пользователей, JWT-аутентификацией и аналитикой переходов.

[![Java](https://img.shields.io/badge/Java-25-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.1-green?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Security](https://img.shields.io/badge/Spring_Security-JWT-red?style=flat-square)](https://jwt.io/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3-brightgreen?style=flat-square&logo=swagger)](http://localhost:8080/swagger-ui/index.html)

---

## 🚀 Основной функционал

* **Генерация коротких ссылок:** Быстрое создание уникальных коротких идентификаторов для длинных URL.
* **HTTP 302 Redirect:** Мгновенное перенаправление пользователя на целевой ресурс по короткому коду.
* **JWT Аутентификация:** Регистрация и вход с шифрованием паролей (`BCrypt`) и выпуском Bearer токенов (срок действия 24 часа).
* **Сбор статистики:** Автоматический подсчет количества переходов по каждой ссылке.
* **Интерактивная документация:** Встроенный Swagger UI для тестирования всех эндпоинтов из браузера.

---

## 🛠 Технологический стек

* **Язык:** Java 25
* **Фреймворк:** Spring Boot 4.1.1 (WebMVC, Data JPA)
* **База данных:** PostgreSQL
* **Безопасность:** Spring Security Crypto + JJWT 0.12.6
* **Документация:** Springdoc OpenAPI UI 3.1.0
* **Сборщик:** Maven

---

## 📡 Спецификация REST API

### 🔐 Авторизация (`/api/v1/auth`)
| Метод | Эндпоинт | Описание | Доступ |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/auth/register` | Регистрация нового пользователя | Public |
| `POST` | `/api/v1/auth/login` | Вход и получение JWT-токена | Public |

### 🔗 Ссылки (`/api/v1/urls`)
| Метод | Эндпоинт | Описание | Доступ |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/urls` | Создать короткую ссылку | Bearer JWT |
| `GET` | `/api/v1/urls` | Список всех ссылок текущего пользователя | Bearer JWT |
| `GET` | `/{shortCode}` | Редирект на оригинальный URL (+1 к кликам) | Public |
| `GET` | `/api/v1/urls/{shortCode}/stats` | Получить статистику переходов | Bearer JWT |
| `DELETE` | `/api/v1/urls/{shortCode}` | Удалить конкретную ссылку | Bearer JWT |
| `DELETE` | `/api/v1/urls` | Очистить все ссылки пользователя | Bearer JWT |

---

## ⚙️ Запуск проекта локально

### 1. Требования
* JDK 25
* PostgreSQL (порт по умолчанию в настройках: `5433`, база: `shorturldb`)

### 2. Настройка `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/shorturldb
spring.datasource.username=postgres
spring.datasource.password=твой_пароль
app.jwt.secret=твой_секретный_ключ
```

### 3. Сборка и старт
```bash
# Сборка проекта
./mvnw clean package

# Запуск приложения
./mvnw spring-boot:run
```

После старта документация Swagger доступна по адресу:  
👉 `http://localhost:8080/swagger-ui/index.html`
