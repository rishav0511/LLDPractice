package org.example.limiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateRateLimiter implements RateLimiter {
    private double refillRate; // tokens per second
    private int capacity; // maximum number of tokens
    private final Map<String, Long> lastRefillTimestamp = new ConcurrentHashMap<>();
    private final Map<String, Integer> userTokens = new ConcurrentHashMap<>();

    public TokenBucketRateRateLimiter(int capacity, double refillRate) {
        this.refillRate = refillRate;
        this.capacity = capacity;
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        lastRefillTimestamp.putIfAbsent(userId, currentTime);
        userTokens.putIfAbsent(userId, capacity);
        long lastRefill = lastRefillTimestamp.get(userId);
        long elapsedTime = (currentTime - lastRefill) / 1000;
        if (elapsedTime > 0) {
            int newTokens = Math.min(capacity, userTokens.get(userId) + (int)(elapsedTime*refillRate));
            userTokens.put(userId, newTokens);
            lastRefillTimestamp.put(userId, currentTime);
        }
        if (userTokens.get(userId) > 0) {
            userTokens.put(userId, userTokens.get(userId)-1);
            return true;
        }
        return false;
    }
}
