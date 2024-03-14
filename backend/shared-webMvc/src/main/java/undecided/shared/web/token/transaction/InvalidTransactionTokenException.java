package undecided.shared.web.token.transaction;

import java.io.Serial;

/**
 * トランザクショントークンの不正例外
 */
public class InvalidTransactionTokenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_MESSAGE = "Invalid Transaction Token Exception !!!";

    /**
     * No argument constructor
     */
    public InvalidTransactionTokenException() {
        this(DEFAULT_MESSAGE);
    }

    /**
     * この例外がスローされたときに表示されるメッセージ文字列を受け取る単一引数コンストラクター
     *
     * @param message メッセージ文字列
     */
    public InvalidTransactionTokenException(String message) {
        super(message);
    }
}
