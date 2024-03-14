package undecided.shared.web.logging;

import jakarta.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpSessionEventLoggingListener implements HttpSessionListener,
        HttpSessionAttributeListener,
        HttpSessionActivationListener {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(
            HttpSessionEventLoggingListener.class);

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSION_ID#{} sessionWillPassivate : {}", session
                    .getId(), se.getSource());
        }
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSION_ID#{} sessionDidActivate : {}", session
                    .getId(), se.getSource());
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSION_ID#{} attributeAdded : {}={}", session.getId(),
                    se.getName(), se.getValue());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSION_ID#{} attributeRemoved : {}={}", session
                    .getId(), se.getName(), se.getValue());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        if (logger.isTraceEnabled()) {
            HttpSession session = se.getSession();
            logger.trace("SESSION_ID#{} attributeReplaced : {}={}", session
                    .getId(), se.getName(), se.getValue());
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSION_ID#{} sessionCreated : {}", session.getId(), se
                    .getSource());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (logger.isDebugEnabled()) {
            HttpSession session = se.getSession();
            logger.debug("SESSION_ID#{} sessionDestroyed : {}", session.getId(),
                    se.getSource());
        }
    }

}
