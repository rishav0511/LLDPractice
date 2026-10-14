package org.example;

import org.example.limiter.*;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(String type, int maxRequests, long windowSizeMillis) {
        if (maxRequests <= 0 || windowSizeMillis <= 0) {
            throw new IllegalArgumentException("Request limit and window size must be positive.");
        }
        return switch (type) {
            case "fixed" -> new FixedWindowRateRateLimiter(maxRequests, windowSizeMillis);
            case "sliding" -> new SlidingWindowRateRateLimiter(maxRequests, windowSizeMillis);
            case "token-bucket" ->
                    new TokenBucketRateRateLimiter(maxRequests, (1.0 * maxRequests / windowSizeMillis * 1000));
            case "leaky-bucket" ->
                    new LeakyBucketRateRateLimiter(maxRequests, Math.max(1, windowSizeMillis / maxRequests));
            default -> throw new IllegalArgumentException("Unsupported type.");
        };
    }
}
