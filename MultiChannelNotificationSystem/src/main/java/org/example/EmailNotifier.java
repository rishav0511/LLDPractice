package org.example;

public class EmailNotifier implements Notifier {
    @Override
    public void sendNotification(Notification notification) {
        // Logic to send email notification
        System.out.println("Sending email notification: " + notification.getMessage()
                + " with severity: " + notification.getSeverity());
    }
}
