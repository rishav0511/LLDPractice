package org.example.Processor;

import org.example.Appender.LogAppender;

public class InfoLogProcessor extends LogProcessor {
    public InfoLogProcessor(LogAppender logAppender, LogProcessor nextProcessor) {
        super(LogProcessor.INFO, logAppender, nextProcessor);
    }
}
