package org.example;

public class CannotMakeChangeException extends RuntimeException {
    public CannotMakeChangeException(String msg) {
        super(msg);
    }
}
