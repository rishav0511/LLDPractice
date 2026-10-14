package org.example;

public class TokenBucket implements Limiter{
    

    @Override
    public boolean allowRequest(String path) {

        return false;
    }
}
