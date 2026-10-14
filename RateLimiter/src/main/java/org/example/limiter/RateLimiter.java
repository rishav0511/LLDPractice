package org.example.limiter;

public interface RateLimiter {
    boolean allowRequest(String userId);
}
