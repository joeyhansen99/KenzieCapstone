package com.kenzie.appserver.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheStore cardCache() {
        return new CacheStore(3600, TimeUnit.SECONDS);
    }
}
