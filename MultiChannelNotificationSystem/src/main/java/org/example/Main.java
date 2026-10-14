package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Notification criticalAlert = new Notification("Database connection lost in US-East", "SEV-1");

        // User 1: Just wants an Email
        System.out.println("--- User 1 (Email Only) ---");
        Notifier user1Notifier = new EmailNotifier();
        user1Notifier.sendNotification(criticalAlert);

        // User 2: Email + SMS + Slack
        System.out.println("\n--- User 2 (Email + SMS + Slack) ---");
        Notifier user2Notifier = new EmailNotifier();
        user2Notifier = new SmsNotifier(user2Notifier, "+1234567890");
        user2Notifier = new SlackNotifier(user2Notifier, "https://hooks.slack.com/services/T00000000/B00000000/XXXXXXXXXXXXXXXXXXXXXXXX");

        // Calling send() once triggers the entire stacked chain
        user2Notifier.sendNotification(criticalAlert);

        // User 3: On-Call Engineer (Everything)
        System.out.println("\n--- User 3 (On-Call Engineer) ---");
        Notifier onCallNotifier = new EmailNotifier();
        onCallNotifier = new SmsNotifier(onCallNotifier, "+0987654321");
        onCallNotifier = new SlackNotifier(onCallNotifier, "https://hooks.slack.com/services/T00000000/B00000000/YYYYYYYYYYYYYYYYYYYYYYYY");
        onCallNotifier = new VoiceCallNotifier(onCallNotifier, "+11234567890");

        onCallNotifier.sendNotification(criticalAlert);

    }
}