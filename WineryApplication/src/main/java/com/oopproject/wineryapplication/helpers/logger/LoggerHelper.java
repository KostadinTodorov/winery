package com.oopproject.wineryapplication.helpers.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класът {@code LoggerHelper} предоставя статични методи за лесно управление на съобщения
 * за логване в различни нива на логиране, като използва {@link Logger}.
 * Той помага за структурирането и централизирането на логическите операции във връзка с
 * логовете в проекти, изискващи регистрация на важни събития или грешки.
 *
 * Основни функционалности на класа включват логване на данни според зададено ниво,
 * като нивата се предоставят чрез {@link LoggerLevels}.
 */
public class LoggerHelper {



    private static Logger logger;

    private LoggerHelper() {};

    /**
     * Методът {@code logData} логва съобщение със зададено ниво на логиране, използвайки
     * класа {@link Logger}. Нивото на логиране се определя от стойността на изброения тип
     * {@link LoggerLevels}, а логгерът се инициализира от името на предоставения {@link Class}.
     *
     * Приема три параметъра:
     * 1. Класът, от който се извиква логването.
     * 2. Нивото на логиране от типа {@link LoggerLevels}.
     * 3. Съобщението, което трябва да бъде записано в лога.
     *
     * В зависимост от нивото на логиране, което се подава, методът извиква съответния метод
     * от {@link Logger}: {@code fatal}, {@code error}, {@code warn}, {@code info}, {@code debug}
     * или {@code trace}.
     *
     * @param clazz Класът, който изпраща заявката за логване. Използва се за създаване
     *              на логгер чрез {@code LogManager.getLogger(clazz)}.
     * @param logLevel Нивото на логиране, представено като тип {@link LoggerLevels}.
     *                 Може да бъде един от следните: {@code FATAL}, {@code ERROR},
     *                 {@code WARN}, {@code INFO}, {@code DEBUG}, {@code TRACE}.
     * @param message Съобщението, което трябва да бъде записано в лога.
     */
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
