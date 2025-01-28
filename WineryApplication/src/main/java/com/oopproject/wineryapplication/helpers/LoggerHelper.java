package com.oopproject.wineryapplication.helpers;

import com.oopproject.wineryapplication.Launcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHelper {



    private static Logger logger;

    private LoggerHelper() {};

    public static void logData(Class <?> clazz, LoggerLevels logLevel, String message) {
        logger = LogManager.getLogger(clazz);

        switch (logLevel.getLoggerLevel().toUpperCase()) {
            case "FATAL":
                logger.fatal(message);
                break;
            case "ERROR":
                logger.error(message);
                break;
            case "WARNING":
                logger.warn(message);
                break;
            case "INFO":
                logger.info(message);
                break;
            case "DEBUG":
                logger.debug(message);
                break;
            case "TRACE":
                logger.trace(message);
                break;
            default:
                //  TODO: add something
                break;
        }
    }
}
