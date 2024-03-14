package undecided.shared.exception;

import undecided.common.primitive.strings.Strings2;

public class DefaultExceptionLevelResolver implements ExceptionLevelResolver {

    private ExceptionCodeResolver exceptionCodeResolver;

    public DefaultExceptionLevelResolver() {
        // do nothing for Default constructor
    }

    public DefaultExceptionLevelResolver(
            ExceptionCodeResolver exceptionCodeResolver) {
        setExceptionCodeResolver(exceptionCodeResolver);
    }

    public void setExceptionCodeResolver(
            ExceptionCodeResolver exceptionCodeResolver) {
        this.exceptionCodeResolver = exceptionCodeResolver;
    }

    @Override
    public ExceptionLevel resolveExceptionLevel(Exception ex) {
        String exceptionCode = resolveExceptionCode(ex);
        if (Strings2.isEmpty(exceptionCode)) {
            return ExceptionLevel.ERROR;
        }

        String exceptionCodePrefix = exceptionCode.substring(0, 1);
        if ("e".equalsIgnoreCase(exceptionCodePrefix)) {
            return ExceptionLevel.ERROR;
        }
        if ("w".equalsIgnoreCase(exceptionCodePrefix)) {
            return ExceptionLevel.WARN;
        }
        if ("i".equalsIgnoreCase(exceptionCodePrefix)) {
            return ExceptionLevel.INFO;
        }
        return ExceptionLevel.ERROR;
    }

    protected String resolveExceptionCode(Exception ex) {
        String exceptionCode = null;
        if (exceptionCodeResolver != null) {
            exceptionCode = exceptionCodeResolver.resolveExceptionCode(ex);
        }
        return exceptionCode;
    }

}
