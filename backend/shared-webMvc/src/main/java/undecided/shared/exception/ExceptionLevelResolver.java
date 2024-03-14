package undecided.shared.exception;

public interface ExceptionLevelResolver {

    ExceptionLevel resolveExceptionLevel(Exception ex);

}
