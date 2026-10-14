package org.example;

import org.example.Appender.AsyncAppender;
import org.example.Appender.ConsoleAppender;
import org.example.Appender.FileAppender;
import org.example.Appender.LogAppender;
import org.example.Processor.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        FileAppender fileAppender = new FileAppender();
        // 2. Wrap the slow FileAppender in our Async Decorator
        // We give it a buffer capacity of 1024 messages
        LogAppender asyncFileAppender = new AsyncAppender(fileAppender, 1024);


        LogProcessor errorLogProcessor = new ErrorLogProcessor(asyncFileAppender, null);
        LogProcessor warnLogProcessor = new WarnLogProcessor(fileAppender, errorLogProcessor);
        LogProcessor infoLogProcessor = new InfoLogProcessor(consoleAppender, warnLogProcessor);
        LogProcessor loggerChain = new DebugLogProcessor(consoleAppender, infoLogProcessor);

        loggerChain.log(LogProcessor.DEBUG, "This is a debug message");
        loggerChain.log(LogProcessor.INFO, "This is an info message");
        loggerChain.log(LogProcessor.WARN, "This is a warning message");
        loggerChain.log(LogProcessor.ERROR, "This is an error message");
    }
}