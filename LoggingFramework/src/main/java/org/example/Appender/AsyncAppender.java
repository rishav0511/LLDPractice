package org.example.Appender;

import lombok.extern.java.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AsyncAppender implements LogAppender {
    private final LogAppender targetAppender; // The actual destination (e.g., FileAppender)
    private final BlockingQueue<String> queue;
    private final Thread workerThread;

    public AsyncAppender(LogAppender targetAppender, int queueCapacity) {
        this.targetAppender = targetAppender;
        this.queue = new ArrayBlockingQueue<>(queueCapacity);

        // Background consumer thread
        this.workerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String message = queue.take();
                    this.targetAppender.append(message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Set as a daemon thread so it doesn't prevent the JVM from shutting down
        this.workerThread.setDaemon(true);
        this.workerThread.setName("Async-Log-Worker");
        this.workerThread.start();
    }

    @Override
    public void append(String message) {
        boolean added = queue.offer(message);
        if (!added) {
            // Fallback strategy if the queue is full (e.g., drop the log, or write to standard error)
            System.err.println("LOG QUEUE FULL - Dropped message: " + message);
            // Alternatively, you could use queue.put(message) to block the main
            // thread until space is available, but that defeats the purpose of async logging.
        }
    }
}
