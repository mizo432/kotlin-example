package undecided.shared.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import undecided.common.primitive.strings.Strings2;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExceptionLogger implements InitializingBean {

    private static final String MONITORING_LOG_LOGGER_SUFFIX = ".Monitoring";

    private final Logger applicationLogger;

    private final Logger monitoringLogger;

    private final Map<ExceptionLevel, LogLevelWrappingLogger> exceptionLevelLoggers = new ConcurrentHashMap<>();

    private final InfoLogger infoLogger;

    private final WarnLogger warnLogger;

    private final ErrorLogger errorLogger;

    private ExceptionCodeResolver exceptionCodeResolver = new SimpleMappingExceptionCodeResolver();

    private ExceptionLevelResolver exceptionLevelResolver;

    private final String PLACEHOLDER_OF_EXCEPTION_CODE = "{0}";

    private final String PLACEHOLDER_OF_EXCEPTION_MESSAGE = "{1}";

    private String logMessageFormat = String.format("[%s] %s",
            PLACEHOLDER_OF_EXCEPTION_CODE, PLACEHOLDER_OF_EXCEPTION_MESSAGE);

    private String defaultCode = "UNDEFINED-CODE";

    private String defaultMessage = "UNDEFINED-MESSAGE";

    private boolean trimLogMessage = true;

    public ExceptionLogger() {
        this(ExceptionLogger.class.getName());
        this.exceptionLevelResolver = new DefaultExceptionLevelResolver();
    }

    public ExceptionLogger(String name) {
        this.applicationLogger = LoggerFactory.getLogger(name);
        this.monitoringLogger = LoggerFactory.getLogger(name
                + MONITORING_LOG_LOGGER_SUFFIX);
        this.infoLogger = new InfoLogger();
        this.warnLogger = new WarnLogger();
        this.errorLogger = new ErrorLogger();
    }

    public void setExceptionCodeResolver(
            ExceptionCodeResolver exceptionCodeResolver) {
        this.exceptionCodeResolver = exceptionCodeResolver;
    }

    public void setExceptionLevelResolver(
            ExceptionLevelResolver exceptionLevelResolver) {
        this.exceptionLevelResolver = exceptionLevelResolver;
    }

    public void setLogMessageFormat(String logMessageFormat) {
        this.logMessageFormat = logMessageFormat;
    }

    public void setTrimLogMessage(boolean trimLogMessage) {
        this.trimLogMessage = trimLogMessage;
    }

    public void setDefaultCode(String defaultCode) {
        this.defaultCode = defaultCode;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public void afterPropertiesSet() {

        validateLogMessageFormat(logMessageFormat);

        if (exceptionLevelResolver == null) {
            exceptionLevelResolver = new DefaultExceptionLevelResolver(exceptionCodeResolver);
        }
        registerExceptionLevelLoggers(ExceptionLevel.INFO, infoLogger);
        registerExceptionLevelLoggers(ExceptionLevel.WARN, warnLogger);
        registerExceptionLevelLoggers(ExceptionLevel.ERROR, errorLogger);
    }

    public void log(Exception ex) {
        ExceptionLevel level = exceptionLevelResolver.resolveExceptionLevel(ex);
        LogLevelWrappingLogger logger = null;
        if (level != null) {
            logger = exceptionLevelLoggers.get(level);
        }
        if (logger == null) {
            logger = errorLogger;
        }
        log(ex, logger);
    }

    public void info(Exception ex) {
        log(ex, infoLogger);
    }

    public void warn(Exception ex) {
        log(ex, warnLogger);
    }

    public void error(Exception ex) {
        log(ex, errorLogger);
    }

    protected void validateLogMessageFormat(String logMessageFormat) {
        if (logMessageFormat == null || !logMessageFormat.contains(
                PLACEHOLDER_OF_EXCEPTION_CODE) || !logMessageFormat.contains(
                PLACEHOLDER_OF_EXCEPTION_MESSAGE)) {
            String message = "logMessageFormat must have placeholder({0} and {1})."
                    + " {0} is replaced with exception code."
                    + " {1} is replaced with exception message. current logMessageFormat is \""
                    + logMessageFormat + "\".";
            throw new IllegalArgumentException(message);
        }
    }

    protected String resolveExceptionCode(Exception ex) {
        String exceptionCode = null;
        if (exceptionCodeResolver != null) {
            exceptionCode = exceptionCodeResolver.resolveExceptionCode(ex);
        }
        return exceptionCode;
    }

    protected String makeLogMessage(Exception ex) {
        String exceptionCode = resolveExceptionCode(ex);
        return formatLogMessage(exceptionCode, ex.getMessage());
    }

    protected String formatLogMessage(String exceptionCode,
                                      String exceptionMessage) {

        String bindingExceptionCode = exceptionCode;
        String bindingExceptionMessage = exceptionMessage;
        if (!Strings2.hasText(bindingExceptionCode)) {
            bindingExceptionCode = defaultCode;
        }
        if (!Strings2.hasText(bindingExceptionMessage)) {
            bindingExceptionMessage = defaultMessage;
        }

        String message = MessageFormat.format(logMessageFormat,
                bindingExceptionCode, bindingExceptionMessage);
        if (trimLogMessage) {
            message = message.trim();
        }
        return message;
    }

    protected void registerExceptionLevelLoggers(ExceptionLevel level,
                                                 LogLevelWrappingLogger logger) {
        this.exceptionLevelLoggers.put(level, logger);
    }

    protected Logger getApplicationLogger() {
        return applicationLogger;
    }

    protected Logger getMonitoringLogger() {
        return monitoringLogger;
    }

    private void log(Exception ex, LogLevelWrappingLogger logger) {
        if (!logger.isEnabled()) {
            return;
        }
        String logMessage = makeLogMessage(ex);
        logger.log(logMessage, ex);
    }

    protected interface LogLevelWrappingLogger {

        /**
         * Determines if the logger is valid.
         *
         * @return Returns <code>true</code> if valid.
         */
        boolean isEnabled();

        /**
         * Outputs the log.
         *
         * @param logMessage log message
         * @param ex         Exception
         */
        void log(String logMessage, Exception ex);

    }

    private final class InfoLogger implements LogLevelWrappingLogger {

        @Override
        public boolean isEnabled() {
            return monitoringLogger.isInfoEnabled() || applicationLogger
                    .isInfoEnabled();
        }

        @Override
        public void log(String logMessage, Exception ex) {
            if (monitoringLogger.isInfoEnabled()) {
                monitoringLogger.info(logMessage);
            }
            if (applicationLogger.isInfoEnabled()) {
                applicationLogger.info(logMessage, ex);
            }
        }
    }

    private final class WarnLogger implements LogLevelWrappingLogger {

        @Override
        public boolean isEnabled() {
            return monitoringLogger.isWarnEnabled() || applicationLogger
                    .isWarnEnabled();
        }

        @Override
        public void log(String logMessage, Exception ex) {
            if (monitoringLogger.isWarnEnabled()) {
                monitoringLogger.warn(logMessage);
            }
            if (applicationLogger.isWarnEnabled()) {
                applicationLogger.warn(logMessage, ex);
            }
        }
    }

    private final class ErrorLogger implements LogLevelWrappingLogger {

        @Override
        public boolean isEnabled() {
            return monitoringLogger.isErrorEnabled() || applicationLogger
                    .isErrorEnabled();
        }

        @Override
        public void log(String logMessage, Exception ex) {
            if (monitoringLogger.isErrorEnabled()) {
                monitoringLogger.error(logMessage);
            }
            if (applicationLogger.isErrorEnabled()) {
                applicationLogger.error(logMessage, ex);
            }
        }
    }

}
