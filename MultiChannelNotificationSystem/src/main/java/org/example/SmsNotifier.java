package org.example;

public class SmsNotifier extends NotifierDecorator {
    private String phoneNumber;

    public SmsNotifier(Notifier notifier, String phoneNumber) {
        super(notifier);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotification(Notification notification) {
        super.sendNotification(notification);
        // Logic to send SMS notification
        System.out.println("Sending SMS notification to " + phoneNumber + ": " + notification.getMessage()
                + " with severity: " + notification.getSeverity());
    }

}
