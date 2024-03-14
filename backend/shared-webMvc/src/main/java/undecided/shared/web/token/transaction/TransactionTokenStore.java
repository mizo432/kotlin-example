package undecided.shared.web.token.transaction;

public interface TransactionTokenStore {

    String getAndClear(TransactionToken token);

    void remove(TransactionToken token);

    String createAndReserveTokenKey(String tokenName);

    void store(TransactionToken token);
}
