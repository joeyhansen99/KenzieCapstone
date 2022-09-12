package com.kenzie.appserver.config;

import com.kenzie.appserver.service.model.Card;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class CacheStore {
    private Cache<String, Card> cache;

    public CacheStore(int expiry, TimeUnit timeUnit) {
        // initialize the cache
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(expiry, timeUnit)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public Card get(String key) {
        return cache.getIfPresent(key);
    }

    public void evict(String key) {
        cache.invalidate(key);
    }

    public void add(String key, Card value) {
        cache.put(key,value);
    }
}
