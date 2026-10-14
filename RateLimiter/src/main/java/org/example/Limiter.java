package org.example;

public interface Limiter {
    boolean allowRequest(String path);
}
