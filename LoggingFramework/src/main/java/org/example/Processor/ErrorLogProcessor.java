package org.example.Processor;

import org.example.Appender.LogAppender;

public class ErrorLogProcessor extends LogProcessor {
    public ErrorLogProcessor(LogAppender logAppender, LogProcessor nextProcessor) {
        super(LogProcessor.ERROR, logAppender, nextProcessor);
    }
}
