package undecided.shared.web.token.transaction;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import undecided.shared.web.token.TokenStringGenerator;

import java.io.Serial;

import static undecided.common.primitive.objects.Objects2.nonNull;

public class TransactionTokenInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionTokenInterceptor.class);

    public static final String TOKEN_CONTEXT_REQUEST_ATTRIBUTE_NAME =
            TransactionTokenInterceptor.class
                    .getName() + ".TOKEN_CONTEXT";

    public static final String NEXT_TOKEN_REQUEST_ATTRIBUTE_NAME = TransactionTokenInterceptor.class
            .getName() + ".NEXT_TOKEN";

    public static final String TOKEN_REQUEST_PARAMETER = "_TRANSACTION_TOKEN";

    private static final TransactionToken INVALID_TOKEN = new TransactionToken(null, null, null) {
        @Serial
        private static final long serialVersionUID = 674844591801033738L;

        @Override
        public boolean valid() {
            return false;
        }
    };

    private final TransactionTokenInfoStore tokenInfoStore;

    private final TransactionTokenStore tokenStore;

    private final TokenStringGenerator generator;

    public TransactionTokenInterceptor() {
        this(new TokenStringGenerator(), new TransactionTokenInfoStore(),
                new HttpSessionTransactionTokenStore());
    }

    public TransactionTokenInterceptor(int sizePerTokenName) {
        this(new TokenStringGenerator(), new TransactionTokenInfoStore(),
                new HttpSessionTransactionTokenStore(sizePerTokenName));
    }

    public TransactionTokenInterceptor(TokenStringGenerator generator,
                                       TransactionTokenInfoStore tokenInfoStore,
                                       TransactionTokenStore tokenStore) {
        this.generator = generator;
        this.tokenInfoStore = tokenInfoStore;
        this.tokenStore = tokenStore;
    }

    @Override
    public boolean preHandle(final @NotNull HttpServletRequest request,
                             final @NotNull HttpServletResponse response, final @NotNull Object handler) {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        LOGGER.trace("preHandle");

        TransactionTokenInfo tokenInfo = tokenInfoStore.getTransactionTokenInfo(
                handlerMethod);

        TransactionToken receivedToken = INVALID_TOKEN;
        if (tokenInfo.needValidate()) {
            receivedToken = createReceivedToken(request);
            if (!validateToken(receivedToken, tokenStore, tokenInfo)) {
                processTransactionTokenError(receivedToken);
            }
        }
        if (tokenInfo.getTransactionTokenType() == TransactionTokenType.BEGIN) {
            String tokenStr = request.getParameter(TOKEN_REQUEST_PARAMETER);
            if (nonNull(tokenStr)) {
                removeToken(new TransactionToken(tokenStr));
            }
        }

        TransactionTokenContextImpl tokenContext = new TransactionTokenContextImpl(tokenInfo,
                receivedToken);

        request.setAttribute(TOKEN_CONTEXT_REQUEST_ATTRIBUTE_NAME,
                tokenContext);

        return true;
    }

    protected void processTransactionTokenError(
            TransactionToken receivedToken) {
        LOGGER.trace("#processTransactionTokenError(TransactionToken)");
        removeToken(receivedToken);
        throw new InvalidTransactionTokenException();
    }

    TransactionToken createReceivedToken(final HttpServletRequest request) {
        LOGGER.trace("#createReceivedToken(HttpServletRequest)");
        String tokenStr = request.getParameter(TOKEN_REQUEST_PARAMETER);
        return new TransactionToken(tokenStr);

    }

    boolean validateToken(final TransactionToken receivedToken,
                          final TransactionTokenStore tokenStore,
                          final TransactionTokenInfo tokenInfo) {
        LOGGER.trace("#validateToken(TransactionToken,TransactionTokenStore,TransactionTokenInfo)");

        if (receivedToken.valid() && receivedToken.getTokenName().equals(
                tokenInfo.getTokenName())) {

            String storedToken = tokenStore.getAndClear(receivedToken);
            return storedToken != null && storedToken.equals(receivedToken
                    .getTokenValue());
        }
        return false;
    }

    @Override
    public void postHandle(final @NotNull HttpServletRequest request,
                           final @NotNull HttpServletResponse response, final @NotNull Object handler,
                           final ModelAndView modelAndView) {

        LOGGER.trace("#postHandle");

        if (!(handler instanceof HandlerMethod)) {
            return;
        }

        TransactionTokenContextImpl tokenContext = (TransactionTokenContextImpl) request
                .getAttribute(TOKEN_CONTEXT_REQUEST_ATTRIBUTE_NAME);

        switch (tokenContext.getReserveCommand()) {
            case CREATE_TOKEN:
                LOGGER.trace("CREATE_TOKEN");
                createToken(request, request.getSession(true), tokenContext
                        .getTokenInfo(), generator, tokenStore);
                break;
            case UPDATE_TOKEN:
                LOGGER.trace("UPDATE_TOKEN");
                updateToken(request, request.getSession(true), tokenContext
                                .getReceivedToken(), tokenContext.getTokenInfo(), generator,
                        tokenStore);
                break;
            case REMOVE_TOKEN:
                LOGGER.trace("REMOVE_TOKEN");
                removeToken(tokenContext.getReceivedToken());
                break;
            case KEEP_TOKEN:
                LOGGER.trace("KEEP_TOKEN");
                keepToken(request, tokenContext.getReceivedToken(), tokenContext
                        .getTokenInfo(), tokenStore);
                break;
            default:
                // noop
                break;
        }

    }

    @Override
    public void afterCompletion(final @NotNull HttpServletRequest request,
                                final @NotNull HttpServletResponse response, final @NotNull Object handler,
                                final Exception ex) {
        LOGGER.trace("afterCompletion");

        if (ex != null) {
            TransactionTokenContextImpl tokenContext = (TransactionTokenContextImpl) request
                    .getAttribute(TOKEN_CONTEXT_REQUEST_ATTRIBUTE_NAME);
            if (tokenContext != null) {
                TransactionToken token = tokenContext.getReceivedToken();
                removeToken(token);
            }
        }

    }

    void updateToken(HttpServletRequest request, HttpSession session,
                     TransactionToken receivedToken, TransactionTokenInfo tokenInfo,
                     TokenStringGenerator generator, TransactionTokenStore tokenStore) {
        TransactionToken nextToken = new TransactionToken(tokenInfo
                .getTokenName(), receivedToken.getTokenKey(), generator
                .generate(session.getId()));
        tokenStore.store(nextToken);
        request.setAttribute(NEXT_TOKEN_REQUEST_ATTRIBUTE_NAME, nextToken);
    }

    void createToken(HttpServletRequest request, HttpSession session,
                     TransactionTokenInfo tokenInfo, TokenStringGenerator generator,
                     TransactionTokenStore tokenStore) {
        TransactionToken nextToken;
        synchronized (WebUtils.getSessionMutex(session)) {
            String tokenKey = tokenStore.createAndReserveTokenKey(tokenInfo
                    .getTokenName());
            nextToken = new TransactionToken(tokenInfo
                    .getTokenName(), tokenKey, generator.generate(session
                    .getId()));
            tokenStore.store(nextToken);
        }
        LOGGER.trace("nextToken:" + nextToken.getTokenString());
        request.setAttribute(NEXT_TOKEN_REQUEST_ATTRIBUTE_NAME, nextToken);
    }

    void removeToken(TransactionToken receivedToken) {
        if (receivedToken.valid()) {
            tokenStore.remove(receivedToken);
        }
    }


    void keepToken(HttpServletRequest request, TransactionToken receivedToken,
                   TransactionTokenInfo tokenInfo, TransactionTokenStore tokenStore) {
        tokenStore.store(receivedToken);
        request.setAttribute(NEXT_TOKEN_REQUEST_ATTRIBUTE_NAME, receivedToken);
    }
}
