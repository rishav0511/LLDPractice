package org.example.limiter;

import java.util.Map;
import java.util.HashMap;

public class FixedWindowRateRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeMillis;
    // Standard HashMap is fine because the method is synchronized
    private final Map<String, Integer> requestCounts = new HashMap<>();
    private long windowStart;

    public FixedWindowRateRateLimiter(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
        this.windowStart = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();

        // 1. Reset the window and clear ALL users if time expired
        if (currentTime - windowStart >= windowSizeMillis) {
            requestCounts.clear();
            windowStart = currentTime;
        }

        // 2. Check limits BEFORE incrementing
        int count = requestCounts.getOrDefault(userId, 0);
        if (count >= maxRequests) {
            return false;
        }

        // 3. Increment and allow
        requestCounts.put(userId, count + 1);
        return true;
    }
}
