package undecided.shared.exception;

import undecided.shared.message.ResultMessage;
import undecided.shared.message.ResultMessages;

import java.io.Serial;

public class ResourceNotFoundException extends
        ResultMessagesNotificationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(ResultMessages.error().add(ResultMessage.fromText(message)));
    }

    public ResourceNotFoundException(ResultMessages messages) {
        super(messages);
    }

    public ResourceNotFoundException(ResultMessages messages, Throwable cause) {
        super(messages, cause);
    }

}
