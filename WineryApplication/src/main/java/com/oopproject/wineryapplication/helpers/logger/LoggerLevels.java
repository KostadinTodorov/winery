package com.oopproject.wineryapplication.helpers.logger;

/**
 * Изброимият тип {@code LoggerLevels} представлява различните нива на логиране,
 * които могат да се използват в приложение за управление на логове.
 * Нивата на логиране се използват за класифициране на съобщенията в лога
 * според тяхната важност или предназначение.
 *
 * Наличните нива са:
 * <ul>
 *     <li>{@code FATAL} - За критични събития, които водят до прекъсване на изпълнението.</li>
 *     <li>{@code ERROR} - За съобщения за грешки, които не прекъсват приложението, но изискват внимание.</li>
 *     <li>{@code WARN} - За предупреждения за потенциални проблеми.</li>
 *     <li>{@code INFO} - За информационни съобщения, които описват нормалното протичане на програмата.</li>
 *     <li>{@code DEBUG} - За допълнителна информация, която е полезна за дебъгване.</li>
 *     <li>{@code TRACE} - За детайлно проследяване на изпълнението, обикновено при отстраняване на проблеми.</li>
 * </ul>
 *
 * Всяко ниво е свързано със съответното текстово представяне
 * (например: {@code "fatal"}, {@code "error"}, {@code "warn"} и т.н.),
 * което може да се получи чрез метода {@link #getLoggerLevel()}.
 */
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
