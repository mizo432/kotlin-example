package undecided.shared.web.token.transaction;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;
import undecided.common.precondition.ObjectPreconditions;
import undecided.shared.web.token.TokenStringGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import static undecided.common.primitive.objects.Objects2.isNull;
import static undecided.common.primitive.objects.Objects2.nonNull;

/**
 * HTTP セッションを使用してトークンを格納する Transaction TokenStore インターフェースの実装
 */
public class HttpSessionTransactionTokenStore implements TransactionTokenStore {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(
            HttpSessionTransactionTokenStore.class);

    /**
     * セッション スコープ内のトークン ホルダーの属性名
     */
    public static final String TOKEN_HOLDER_SESSION_ATTRIBUTE_PREFIX =
            HttpSessionTransactionTokenStore.class
                    .getName() + "_TOKEN_";

    /**
     * トークン名ごとのデフォルトのトークン サイズ
     */
    public static final int NO_OF_TOKENS_PER_TOKEN_NAME = 10;

    /**
     * トークンを生成するためのデフォルトの再試行回数
     */
    public static final int DEFAULT_RETRY_CREATE_TOKEN_NAME = 10;

    /**
     * tokenName ごとに許可される transactionTokens の数
     */
    private final int transactionTokensPerTokenName;

    /**
     * 特定の tokenName から sessionAttributeName を作成するための再試行回数
     */
    private final int retryCreateTokenName;

    /**
     * トークン文字列のジェネレータ
     */
    private final TokenStringGenerator generator;

    /**
     * デフォルトのコンストラクタ.
     * <pre>
     * デフォルトでは、tokenName ごとの tokenKeys の数は 10 に設定されています。 tokenName は 10 に設定されています。
     * </pre>
     */
    public HttpSessionTransactionTokenStore() {
        this(new TokenStringGenerator(), NO_OF_TOKENS_PER_TOKEN_NAME,
                DEFAULT_RETRY_CREATE_TOKEN_NAME);
    }

    /**
     * コンストラクタ.
     * <pre>
     * 引数として transactionTokensPerTokenName を取ります
     * </pre>
     * <pre>
     * transactionTokenSizePerTokenName は、一度に各 tokenName のセッションに格納できるトランザクション トークンの数を示します。
     * </pre>
     *
     * @param transactionTokenSizePerTokenName 各 tokenName に許可されるトークンの数 (0より大きい値)
     * @throws IllegalArgumentException sizePerTokenName が 0 以下
     */
    public HttpSessionTransactionTokenStore(
            int transactionTokenSizePerTokenName) {
        this(new TokenStringGenerator(), transactionTokenSizePerTokenName,
                DEFAULT_RETRY_CREATE_TOKEN_NAME);
    }

    /**
     * コンストラクター.
     * <pre>
     *   transactionTokensPerTokenName と retryCreateTokenName を引数として取ります
     * </pre>
     * <pre>
     * transactionTokensPerTokenName は、一度に各 tokenName のセッションに格納できるトランザクション トークンの数を示します。
     * retryCreateTokenName は、トークン名の作成を再試行する回数を示します。
     * </pre>
     *
     * @param transactionTokenSizePerTokenName 各 tokenName に許可されるトークンの数 (0より大きくなければいけません)
     * @param retryCreateTokenName             tokenName 作成の再試行回数 (0 より大きい必要があります)
     * @throws IllegalArgumentException sizePerTokenName が (0 以下) または retryCreateTokenName が (0 以下)
     */
    public HttpSessionTransactionTokenStore(
            int transactionTokenSizePerTokenName, int retryCreateTokenName) {
        this(new TokenStringGenerator(), transactionTokenSizePerTokenName,
                retryCreateTokenName);
    }

    /**
     * コンストラクタ。
     * <pre>
     * トークン ストアのカスタマイズを有効にします
     * </pre>
     *
     * @param generator                        {@link TokenStringGenerator} インスタンス (null
     *                                         であってはなりません)
     * @param transactionTokenSizePerTokenName 各 tokenName に許可されるトークンの数 (0より大きくなければいけません)
     * @param retryCreateTokenName             tokenName 作成の再試行回数 (0 より大きい必要があります)
     * @throws IllegalArgumentException generator が null または sizePerTokenName が (0 以下) または
     *                                  retryCreateTokenName が (0 以下)
     */
    public HttpSessionTransactionTokenStore(
            final TokenStringGenerator generator,
            final int transactionTokenSizePerTokenName,
            final int retryCreateTokenName) {
        if (generator == null) {
            throw new IllegalArgumentException("generator must not be null");
        }
        if (transactionTokenSizePerTokenName <= 0) {
            throw new IllegalArgumentException(
                    "transactionTokenSizePerTokenName must be greater than 0");
        }
        if (retryCreateTokenName <= 0) {
            throw new IllegalArgumentException("retryCreateTokenName must be greater than 0");
        }
        this.generator = generator;
        this.transactionTokensPerTokenName = transactionTokenSizePerTokenName;
        this.retryCreateTokenName = retryCreateTokenName;
    }

    /**
     * このメソッドの引数として受け取った {@link TransactionToken} に対応する、セッションに格納されている値を取得します。
     * <pre>
     * 同じ transactionToken インスタンスに対応するこの値は、一度だけ取得できます。
     * 一度 値がフェッチされ、その値がセッションからクリアされます。
     * 同じトランザクション トークン インスタンスに対してこのメソッドをさらに呼び出すと、<code>null</code> が返されます。
     * </pre>
     *
     * @throws IllegalArgumentException generator is null
     * @see TransactionTokenStore#getAndClear(TransactionToken)
     */
    @Override
    public String getAndClear(TransactionToken token) {
        String key = createSessionAttributeName(token);
        HttpSession session = getSession();
        Object mutex = getMutex(session);
        TokenHolder tokenHolder;
        synchronized (mutex) {
            tokenHolder = (TokenHolder) session.getAttribute(key);
            if (nonNull(tokenHolder)) {
                session.setAttribute(key, new TokenHolder(null, System
                        .currentTimeMillis()));
            }
        }
        if (nonNull(tokenHolder)) {
            return tokenHolder.getToken();
        }

        return null;
    }

    /**
     * このメソッドに引数として渡されたトランザクション Token インスタンスに対応するセッション属性を削除します
     *
     * @see TransactionTokenStore#remove(TransactionToken)
     */
    @Override
    public void remove(TransactionToken token) {
        String key = createSessionAttributeName(token);
        HttpSession session = getSession();
        Object mutex = getMutex(session);
        synchronized (mutex) {
            try {
                session.removeAttribute(key);
            } catch (IllegalStateException e) {
                logger.debug("session is already invalidated.", e);
            }
        }
    }

    /**
     * 新しいトークン キーを作成し、HttpSession でそれを予約します トークンの場合、古いセット トークンを削除します。 サイズは、同じ名前空間内の
     * {@link #transactionTokensPerTokenName} 以上です。
     *
     * @see TransactionTokenStore#createAndReserveTokenKey(String)
     */
    @Override
    public String createAndReserveTokenKey(String tokenName) {
        String tokenNamePrefix = TOKEN_HOLDER_SESSION_ATTRIBUTE_PREFIX
                + tokenName;
        Set<String> sessionAttributeNames = new HashSet<>();
        HttpSession session = getSession();
        Object mutex = getMutex(session);
        String tokenKey = null;
        synchronized (mutex) {
            Enumeration<String> tokenNameEnumeration = session
                    .getAttributeNames();
            while (tokenNameEnumeration.hasMoreElements()) {
                String name = tokenNameEnumeration.nextElement();
                // fetch the sessionKeyPrefix (session key with only Token prefix and namespace name) and compare
                if (tokenNamePrefix.equals(name.split(
                        TransactionToken.TOKEN_STRING_SEPARATOR)[0])) {
                    sessionAttributeNames.add(name);
                }
            }

            for (int i = 0, max = sessionAttributeNames.size(); i < max; i++) {
                // do not use while loop to avoid infinite loop
                if (sessionAttributeNames
                        .size() >= transactionTokensPerTokenName) {
                    removeOldTokenName(sessionAttributeNames, session);
                } else {
                    break;
                }
            }

            for (int i = 0; i < retryCreateTokenName; i++) {
                String str = generator.generate(session.getId());
                String name = tokenNamePrefix
                        + TransactionToken.TOKEN_STRING_SEPARATOR + str;
                if (!sessionAttributeNames.contains(name)) {
                    tokenKey = str;
                    break;
                }
            }
        }
        if (isNull(tokenKey)) {
            throw new IllegalStateException("token key generation failed within retry count "
                    + retryCreateTokenName);
        }

        return tokenKey;
    }

    /**
     * セッションから古いトークン名を削除します
     *
     * @param sessionAttributeNames トークン名のセット
     * @param session               HttpSession
     */
    private void removeOldTokenName(Set<String> sessionAttributeNames,
                                    HttpSession session) {
        String oldestTokenName = null;
        TokenHolder oldestTokenHolder = new TokenHolder(null, Long.MAX_VALUE);
        for (String name : sessionAttributeNames) {
            TokenHolder tokenHolder = (TokenHolder) session.getAttribute(name);
            if (tokenHolder.getTimestamp() < oldestTokenHolder.getTimestamp()) {
                oldestTokenName = name;
                oldestTokenHolder = tokenHolder;
            }
        }
        session.removeAttribute(oldestTokenName);
        sessionAttributeNames.remove(oldestTokenName);
    }

    /**
     * <code>TransactionToken</code> で表されるトークンを HTTP セッションに保存します
     * <pre>
     *     保存するセッション属性名には接頭辞が付きます
     * <code>HttpSessionTransactionTokenStore_TOKEN_</code>
     * </pre>
     *
     * @see TransactionTokenStore#store(TransactionToken)
     */
    @Override
    public void store(TransactionToken token) {
        String sessionAttributeKey = createSessionAttributeName(token);
        HttpSession session = getSession();
        Object mutex = getMutex(session);
        synchronized (mutex) {
            session.setAttribute(sessionAttributeKey, new TokenHolder(token
                    .getTokenValue(), System.currentTimeMillis()));
        }
    }

    /**
     * 指定されたセッションからミューテックスを返します
     *
     * @param session セッション
     * @return ミューテックスオブジェクト
     */
    Object getMutex(HttpSession session) {
        return WebUtils.getSessionMutex(session);
    }

    /**
     * リクエストコンテキストからリクエストコンテキストから {@link HttpSession} を返します
     *
     * @return このコンテキストでの httpSession
     */
    HttpSession getSession() {
        return getRequest().getSession(true);
    }

    /**
     * リクエストコンテキストから {@link HttpServletRequest} を返します
     *
     * @return このコンテキストでの http リクエスト
     */
    HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();
    }

    /**
     * トークンから HTTP セッションに保存する属性名を作成します
     *
     * @param token トークン
     * @return 属性名
     */
    String createSessionAttributeName(@NotNull TransactionToken token) {
        ObjectPreconditions.checkNotNull(token,
                () -> new IllegalArgumentException("token must not be null"));

        return TOKEN_HOLDER_SESSION_ATTRIBUTE_PREFIX + token.getTokenName()
                + TransactionToken.TOKEN_STRING_SEPARATOR + token.getTokenKey();
    }

    /**
     * トークンを保持するオブジェクト
     */
    @JsonTypeInfo(use = Id.CLASS)
    private static final class TokenHolder implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * トークン オブジェクトの文字列表現
         */
        private String token;


        /**
         * トークンが保存されたときのタイムスタンプ
         */
        private long timestamp;

        /**
         * コンストラクター
         */
        public TokenHolder() {
        }

        /**
         * コンストラクター
         *
         * @param token     トークン オブジェクトの文字列表現
         * @param timestamp トークンが保存されたときのタイムスタンプ
         */
        public TokenHolder(final String token, final long timestamp) {
            this.token = token;
            this.timestamp = timestamp;
        }

        /**
         * トークン オブジェクトの文字列表現を返します
         *
         * @return トークン オブジェクトの文字列表現
         */
        public String getToken() {
            return token;
        }

        /**
         * トークンが保存されたときのタイムスタンプを返します
         *
         * @return タイムスタンプ
         */
        public long getTimestamp() {
            return timestamp;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

    }
}
