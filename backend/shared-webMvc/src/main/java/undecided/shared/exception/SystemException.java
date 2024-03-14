package undecided.shared.exception;

import java.io.Serial;

/**
 * システム例外の基底クラス.
 * <pre>
 *     システム例外の場合はこの例外を継承証する
 * </pre>
 */
public class SystemException extends RuntimeException implements
        ExceptionCodeProvider {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String code;

    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

}
