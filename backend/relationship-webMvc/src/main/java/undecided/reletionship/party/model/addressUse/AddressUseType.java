package undecided.reletionship.party.model.addressUse;

import lombok.Getter;

@Getter
public enum AddressUseType {
    UNKNOWN(0, "未設定"),
    ADDRESS(1, "住所"),
    ADDRESS2(2, "所在地");

    private final int index;
    private final String caption;

    AddressUseType(int index, String caption) {
        this.index = index;
        this.caption = caption;
    }

}
