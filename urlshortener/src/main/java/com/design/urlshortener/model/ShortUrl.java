package com.design.urlshortener.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document("shortUrl")
@Getter
@Setter
@NoArgsConstructor
public class ShortUrl {
    private String id;
    private String shortUrlId;
    private String longUrl;
    private String email;
    private Instant createdDate;

    public ShortUrl(String shortUrlId, String longUrl, String email, Instant createdDate) {
        this.id = UUID.randomUUID().toString();
        this.shortUrlId = shortUrlId;
        this.longUrl = longUrl;
        this.email = email;
        this.createdDate = createdDate;
    }
}