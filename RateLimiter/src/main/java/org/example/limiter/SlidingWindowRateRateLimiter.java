package org.example.limiter;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private Map<String, Deque<Long>> userRequestTimestamps;

    public SlidingWindowRateRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        userRequestTimestamps = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userRequestTimestamps.putIfAbsent(userId, new LinkedList<>());
        Deque<Long> timestamps = userRequestTimestamps.get(userId);
        while(!timestamps.isEmpty() && currentTime-timestamps.peekFirst()>windowSizeInMillis) {
            timestamps.pollFirst();
        }
        if(timestamps.size()<maxRequests) {
            timestamps.addLast(currentTime);
            return true;
        }
        return false;
    }
}
