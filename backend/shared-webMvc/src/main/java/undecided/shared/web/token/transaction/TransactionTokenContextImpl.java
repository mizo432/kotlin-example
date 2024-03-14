package undecided.shared.web.token.transaction;

/**
 * <code>TransactionToken</code> の特定のインスタンスに関するコンテキスト情報を格納します.
 * <pre>
 * 次の情報を保持します。
 * 1) リクエスト ハンドラ メソッドに付加された Transaction Token Check によって定義される情報。
 * 2) このトークンの有効期間中の任意の時点でトランザクション トークンに対して実行されるアクション。
 * 3) ブラウザから受け取ったトークン
 * </pre>
 */
public class TransactionTokenContextImpl implements TransactionTokenContext {

    /**
     * transactionTokenに関連して実行するコマンドを示します
     */
    enum ReserveCommand {
        CREATE_TOKEN, UPDATE_TOKEN, REMOVE_TOKEN, KEEP_TOKEN, NONE
    }

    ;

    /**
     * TransactionToken に関するメタ情報
     */
    private final TransactionTokenInfo tokenInfo;

    private final TransactionToken receivedToken;

    private final ReserveCommand defaultCommand;

    /**
     * トークンを作成、更新、または削除する指示を示します。ノーアクションはあり得ない
     */
    private final ReserveCommand reserveCommand;

    /**
     * Constructor.
     * <pre>
     * <code>receivedToken</code><code>receivedToken</code>の引数に基づいて<code>reserveCommand</code>を初期化します
     *
     * </pre>
     *
     * @param tokenInfo     トランザクショントークン情報
     * @param receivedToken 受け取ったトークン
     */
    public TransactionTokenContextImpl(final TransactionTokenInfo tokenInfo,
                                       TransactionToken receivedToken) {
        this.tokenInfo = tokenInfo;
        this.receivedToken = receivedToken;

        if (tokenInfo.needCreate()) {
            if (receivedToken.valid()) {
                defaultCommand = ReserveCommand.UPDATE_TOKEN;
            } else {
                defaultCommand = ReserveCommand.CREATE_TOKEN;
            }
            reserveCommand = defaultCommand;
        } else {
            if (tokenInfo.needKeep()) {
                defaultCommand = ReserveCommand.KEEP_TOKEN;
            } else {
                if (receivedToken.valid()) {
                    defaultCommand = ReserveCommand.REMOVE_TOKEN;
                } else {
                    defaultCommand = ReserveCommand.NONE;
                }
            }
            reserveCommand = defaultCommand;
        }
    }

    /**
     * <code>TransactionTokenInfo</code> インスタンスを取得します
     *
     * @return <code>TransactionTokenInfo</code>
     */
    public TransactionTokenInfo getTokenInfo() {
        return tokenInfo;
    }

    /**
     * 予約済みコマンドを返します
     *
     * @return 予約済みコマンド
     */
    public ReserveCommand getReserveCommand() {
        return reserveCommand;
    }

    @Override
    public TransactionToken getReceivedToken() {
        return receivedToken;
    }
}
