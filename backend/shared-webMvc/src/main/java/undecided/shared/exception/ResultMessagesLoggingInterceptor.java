package undecided.shared.exception;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;

public class ResultMessagesLoggingInterceptor implements MethodInterceptor,
        InitializingBean {

    private final ThreadLocal<MethodInvocation> startingPoint = new ThreadLocal<>();

    private ExceptionLogger exceptionLogger = null;

    public void setExceptionLogger(ExceptionLogger exceptionLogger) {
        this.exceptionLogger = exceptionLogger;
    }

    @Override
    public Object invoke(
            @NotNull MethodInvocation invocation) throws Throwable, ResultMessagesNotificationException {

        // set starting point.
        if (startingPoint.get() == null) {
            startingPoint.set(invocation);
        }

        try {

            // invoke method.
            return invocation.proceed();

        } catch (ResultMessagesNotificationException e) {

            // output log.
            if (isStartingPoint(invocation)) {
                logResultMessagesNotificationException(e);
            }
            throw e;

        } finally {

            // clear starting point.
            if (isStartingPoint(invocation)) {
                startingPoint.remove();
            }

        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (exceptionLogger == null) {
            exceptionLogger = new ExceptionLogger(getClass().getName());
            exceptionLogger.afterPropertiesSet();
        }
    }

    protected boolean isStartingPoint(MethodInvocation invocation) {
        return startingPoint.get() == invocation;
    }

    protected void logResultMessagesNotificationException(
            ResultMessagesNotificationException e) {
        exceptionLogger.warn(e);
    }

    protected ExceptionLogger getExceptionLogger() {
        return exceptionLogger;
    }

}
