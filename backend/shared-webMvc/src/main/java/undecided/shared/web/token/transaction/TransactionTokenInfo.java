package undecided.shared.web.token.transaction;

/**
 * 作成された TransactionToken に関するメタ情報が含まれています
 */
public class TransactionTokenInfo {

    private final String tokenName;

    private final TransactionTokenType tokenType;

    public TransactionTokenInfo(final String tokenName,
                                final TransactionTokenType tokenType) {
        this.tokenName = tokenName;
        this.tokenType = tokenType;
    }

    public String getTokenName() {
        return tokenName;
    }

    public TransactionTokenType getTransactionTokenType() {
        return tokenType;
    }

    public boolean needValidate() {
        return tokenType.needValidate();
    }

    public boolean needCreate() {
        return tokenType.needCreate();
    }

    public boolean needKeep() {
        return tokenType.needKeep();
    }

    @Override
    public String toString() {
        return "TransactionTokenInfo [tokenName=" + tokenName
                + ", transitionType=" + tokenType + "]";
    }
}
