package org.example;

public class SlackNotifier extends NotifierDecorator {

    private String slackWebhookUrl;

    public SlackNotifier(Notifier notifier, String slackWebhookUrl) {
        super(notifier);
        this.slackWebhookUrl = slackWebhookUrl;
    }

    @Override
    public void sendNotification(Notification notification) {
        super.sendNotification(notification);
        // Logic to send Slack notification
        System.out.println("Sending Slack notification to " + slackWebhookUrl + ": " + notification.getMessage()
                + " with severity: " + notification.getSeverity());
    }
}
