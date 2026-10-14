package org.example.Processor;

import org.example.Appender.LogAppender;

public abstract class LogProcessor {
    public static int DEBUG = 0;
    public static int INFO = 1;
    public static int WARN = 2;
    public static int ERROR = 3;

    protected int logLevel;
    private final LogAppender logAppender;
    private final LogProcessor nextProcessor;

    public LogProcessor(int logLevel, LogAppender logAppender, LogProcessor nextProcessor) {
        this.logLevel = logLevel;
        this.logAppender = logAppender;
        this.nextProcessor = nextProcessor;
    }

    public void log(int level, String message) {
        if (level == this.logLevel) {
            logAppender.append(message);
        }
        if (nextProcessor != null) {
            nextProcessor.log(level, message);
        }
    }

}
