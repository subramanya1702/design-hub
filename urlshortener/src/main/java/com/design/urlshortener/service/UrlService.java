package com.design.urlshortener.service;

import com.design.urlshortener.cache.UrlCache;
import com.design.urlshortener.dto.ShortUrlRequestDto;
import com.design.urlshortener.exception.BadRequestException;
import com.design.urlshortener.model.ShortUrl;
import com.design.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;

import static com.design.urlshortener.constant.Constants.BASE_62_CHARACTERS;
import static com.design.urlshortener.constant.Constants.BASE_62_CHARACTERS_SIZE;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlCache urlCache;

    @Value("${url.shortener.base.url}")
    private String baseUrl;

    @Autowired
    public UrlService(final UrlRepository urlRepository,
                      final UrlCache urlCache) {
        this.urlRepository = urlRepository;
        this.urlCache = urlCache;
    }

    public String getLongUrl(final String shortUrlId) {
        final String cachedUrl = this.urlCache.get(shortUrlId);
        if (!cachedUrl.isEmpty()) {
            return cachedUrl;
        }

        final ShortUrl shortUrl = this.urlRepository.findByShortUrlId(shortUrlId);
        if (ObjectUtils.isEmpty(shortUrl)) {
            throw new BadRequestException("URL is not valid!");
        }

        return shortUrl.getLongUrl();
    }

    public String createShortUrl(final ShortUrlRequestDto shortUrlRequestDto) {
        final long currentTime = Instant.now().toEpochMilli();
        final String shortUrlId = generateShortUrl(currentTime);

        this.urlRepository.save(new ShortUrl(
                shortUrlId,
                shortUrlRequestDto.getLongUrl(),
                shortUrlRequestDto.getEmail(),
                currentTime)
        );
        this.urlCache.put(shortUrlId, shortUrlRequestDto.getLongUrl());

        return this.baseUrl + shortUrlId;
    }

    private String generateShortUrl(long n) {
        final StringBuilder shortUrlId = new StringBuilder();

        while (n != 0) {
            int remainder = (int) (n % BASE_62_CHARACTERS_SIZE);
            shortUrlId.append(BASE_62_CHARACTERS.charAt(remainder));
            n /= 62;
        }

        return shortUrlId.toString();
    }
}
