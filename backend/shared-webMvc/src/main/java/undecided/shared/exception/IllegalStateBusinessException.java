package undecided.shared.exception;

import undecided.shared.message.ResultMessage;
import undecided.shared.message.ResultMessages;

import java.io.Serial;

public class IllegalStateBusinessException extends ResultMessagesNotificationException {
    @Serial
    private static final long serialVersionUID = 1L;

    public IllegalStateBusinessException(String message) {
        super(ResultMessages.error().add(ResultMessage.fromText(message)));
        
    }

    public IllegalStateBusinessException(ResultMessages messages) {
        super(messages);

    }

    public IllegalStateBusinessException(ResultMessages messages, Throwable cause) {
        super(messages, cause);

    }
}
