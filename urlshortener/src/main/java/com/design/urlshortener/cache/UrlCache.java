package com.design.urlshortener.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UrlCache {

    private final Cache urlCache;

    @Autowired
    public UrlCache(final CacheManager cacheManager) {
        this.urlCache = cacheManager.getCache("urlCache");
    }

    public String get(final String shortUrlId) {
        return Optional.ofNullable(this.urlCache.get(shortUrlId, String.class))
                .orElse("");
    }

    public void put(final String shortUrlId, final String longUrl) {
        this.urlCache.put(shortUrlId, longUrl);
    }
}
