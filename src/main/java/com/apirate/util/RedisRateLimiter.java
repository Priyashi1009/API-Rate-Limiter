package com.apirate.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisRateLimiter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean isWithinRateLimit(String key, int requestsPerMinute) {
        Long currentCount = getCurrentRequestCount(key);
        return currentCount != null && currentCount < requestsPerMinute;
    }

    public void incrementRequestCount(String key) {
        redisTemplate.opsForValue().increment(key, 1);
        redisTemplate.expire(key, 1, TimeUnit.MINUTES); // Expire key in 1 minute
    }

    private Long getCurrentRequestCount(String key) {
        String value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            return Long.parseLong(value);
        }
        return 0L;
    }
}
