package com.oopproject.wineryapplication.helpers.logger;

public enum LoggerLevels {

    FATAL("fatal"),
    ERROR("error"),
    WARN("warn"),
    INFO("info"),
    DEBUG("debug"),
    TRACE("trace");

    private String loggerLevel;

    LoggerLevels(String loggerLevel) {
        this.loggerLevel = loggerLevel;
    }

    public String getLoggerLevel() {
        return loggerLevel;
    }
}
