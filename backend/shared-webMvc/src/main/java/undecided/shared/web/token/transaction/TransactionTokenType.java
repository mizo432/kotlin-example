package undecided.shared.web.token.transaction;

public enum TransactionTokenType {
    NONE(false, false, false),
    BEGIN(false, true, false),
    IN(true, true, false),
    END(true, false, false),
    CHECK(true, false, true);

    private final boolean needValidate;

    private final boolean needCreate;

    private final boolean needKeep;

    TransactionTokenType(final boolean needValidate, final boolean needCreate,
                         final boolean needKeep) {
        this.needValidate = needValidate;
        this.needCreate = needCreate;
        this.needKeep = needKeep;
    }

    public boolean needValidate() {
        return needValidate;
    }

    public boolean needCreate() {
        return needCreate;
    }

    public boolean needKeep() {
        return needKeep;
    }
}
