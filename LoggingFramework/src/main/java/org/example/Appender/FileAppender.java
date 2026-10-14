package org.example.Appender;

public class FileAppender implements LogAppender {
    @Override
    public void append(String message) {
        // For demonstration purposes, we'll just print to the console.
        // In a real implementation, this would write to a file.
        System.out.println("FILE: " + message);
    }
}
