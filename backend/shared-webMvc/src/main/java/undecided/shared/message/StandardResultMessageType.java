package undecided.shared.message;

public enum StandardResultMessageType implements ResultMessageType {
    SUCCESS("success"),
    INFO("info"),
    WARNING("warning"),
    ERROR("error"),
    DANGER("danger"),
    PRIMARY("primary"),
    SECONDARY("secondary"),
    LIGHT("light"),
    DARK("dark");

    private final String type;

    StandardResultMessageType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
