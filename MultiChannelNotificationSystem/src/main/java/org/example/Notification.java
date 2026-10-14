package org.example;

public class Notification {
    private String message;
    private String severity;

    public Notification(String message, String severity) {
        this.message = message;
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public String getSeverity() {
        return severity;
    }
}
