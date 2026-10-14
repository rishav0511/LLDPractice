package org.example;

public class VoiceCallNotifier extends NotifierDecorator {
    private String phoneNumber;

    public VoiceCallNotifier(Notifier notifier, String phoneNumber) {
        super(notifier);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotification(Notification notification) {
        super.sendNotification(notification);
        // Logic to send voice call notification
        System.out.println("Making voice call to " + phoneNumber + ": " + notification.getMessage()
                + " with severity: " + notification.getSeverity());
    }

}