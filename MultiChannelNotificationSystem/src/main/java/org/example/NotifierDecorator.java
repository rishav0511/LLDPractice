package org.example;

public class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    @Override
    public void sendNotification(Notification notification) {
        wrappedNotifier.sendNotification(notification);
    }
}
