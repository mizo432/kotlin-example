package undecided.shared.web.token.transaction;

/**
 * トランザクション トークン コンテキストを管理するための操作を含むインターフェイス
 */
public interface TransactionTokenContext {

    /**
     * 受信したトランザクション トークン オブジェクトを返します
     *
     * @return 受信したトランザクショントークン
     */
    TransactionToken getReceivedToken();
}
