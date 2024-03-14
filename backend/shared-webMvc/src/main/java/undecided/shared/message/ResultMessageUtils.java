package undecided.shared.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.Assert;

import java.util.Locale;

public class ResultMessageUtils {

    private static final Logger logger = LoggerFactory.getLogger(
            ResultMessageUtils.class);

    private ResultMessageUtils() {
    }

    ;

    public static String resolveMessage(ResultMessage message,
                                        MessageSource messageSource,
                                        Locale locale) throws NoSuchMessageException {
        Assert.notNull(messageSource, "messageSource must not be null!");
        Assert.notNull(message, "message must not be null!");
        Assert.notNull(locale, "locale must not be null!");

        String msg;
        String code = message.code();
        if (code != null) {
            // try to resolve from code at first.
            try {
                msg = messageSource.getMessage(code, message.args(), locale);
            } catch (NoSuchMessageException e) {
                String text = message.text();
                if (text != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("message is not found under code '" + code
                                + "' for '" + locale + "'. use '" + text
                                + "' instead", e);
                    }
                    // if ResultMessage has a text, then use it.
                    msg = text;
                } else {
                    // otherwise throw exception
                    throw e;
                }
            }
        } else {
            msg = message.text();
        }
        return msg;
    }

    public static String resolveMessage(ResultMessage message,
                                        MessageSource messageSource) {
        return resolveMessage(message, messageSource, Locale.getDefault());
    }
}
