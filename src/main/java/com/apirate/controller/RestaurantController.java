package com.apirate.controller;

import com.apirate.model.ClientRateLimit;
import com.apirate.repository.ClientRateLimitRepository;
import com.apirate.util.RedisRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RedisRateLimiter redisRateLimiter; 

    @Autowired
    private ClientRateLimitRepository rateLimitRepository;

    @GetMapping("/api/restaurants")
    public List<String> getNearbyRestaurants(@RequestParam String location) {
        String clientId = "HOLA-ROOMS"; 

        // Fetch rate limit from MongoDB
        ClientRateLimit clientRateLimit = rateLimitRepository.findByClientId(clientId);
        if (clientRateLimit == null) {
            throw new RuntimeException("Rate limit configuration not found for client: " + clientId);
        }
        int requestsPerMinute = clientRateLimit.getRequestsPerMinute();

        // Check rate limit using Redis
        String key = clientId + ":" + location; 
        if (!redisRateLimiter.isWithinRateLimit(key, requestsPerMinute)) {
            throw new RuntimeException("Rate limit exceeded. Try again later");
        }

        // Increment request count in Redis
        redisRateLimiter.incrementRequestCount(key);

        
        return Arrays.asList("Restaurant A", "Restaurant B", "Restaurant C");
    }
}
