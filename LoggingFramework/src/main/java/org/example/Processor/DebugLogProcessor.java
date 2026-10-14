package org.example.Processor;

import org.example.Appender.LogAppender;

public class DebugLogProcessor extends LogProcessor {
    public DebugLogProcessor(LogAppender logAppender, LogProcessor nextProcessor) {
        super(LogProcessor.DEBUG, logAppender, nextProcessor);
    }
}
