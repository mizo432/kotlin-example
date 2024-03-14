package undecided.shared.web.token.transaction;

import undecided.common.primitive.strings.Strings2;

import java.io.Serial;
import java.io.Serializable;

public class TransactionToken implements Serializable {

    /**
     * serial version UID.
     */
    @Serial
    private static final long serialVersionUID = -917084514722659745L;

    /**
     * トークンの名称、キー、値を分割するセパレーター
     */
    static final String TOKEN_STRING_SEPARATOR = "~";

    /**
     * トークンの名称
     */
    private final String tokenName;

    /**
     * トークンキー
     */
    private final String tokenKey;

    /**
     * トークン値
     */
    private final String tokenValue;

    public TransactionToken(final String tokenString) {
        String tokenNameTmp = "";
        String tokenKeyTmp = "";
        String tokenValueTmp = "";
        if (Strings2.hasText(tokenString)) {
            String[] strs = tokenString.split(TOKEN_STRING_SEPARATOR);
            if (strs.length == 3) {
                tokenNameTmp = strs[0];
                tokenKeyTmp = strs[1];
                tokenValueTmp = strs[2];
            }
        }
        this.tokenName = tokenNameTmp;
        this.tokenKey = tokenKeyTmp;
        this.tokenValue = tokenValueTmp;
    }

    public TransactionToken(final String tokenName, final String tokenKey,
                            final String tokenValue) {
        this.tokenName = tokenName;
        this.tokenKey = tokenKey;
        this.tokenValue = tokenValue;
    }

    public String getTokenName() {
        return tokenName;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public boolean valid() {
        if (Strings2.isEmpty(tokenKey)) {
            return false;
        }
        if (Strings2.isEmpty(tokenValue)) {
            return false;
        }
        return Strings2.isNotEmpty(tokenName);
    }

    public String getTokenString() {
        return tokenName + TOKEN_STRING_SEPARATOR + tokenKey
                + TOKEN_STRING_SEPARATOR + tokenValue;
    }

}
