package org.example.Processor;

import org.example.Appender.LogAppender;

public class WarnLogProcessor extends LogProcessor {
    public WarnLogProcessor(LogAppender logAppender, LogProcessor nextProcessor) {
        super(LogProcessor.WARN, logAppender, nextProcessor);
    }
}
