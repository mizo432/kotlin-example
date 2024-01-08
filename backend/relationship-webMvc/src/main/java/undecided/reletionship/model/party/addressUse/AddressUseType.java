package undecided.reletionship.model.party.addressUse;

import lombok.Getter;

@Getter
public enum AddressUseType {
    UNKNOWN(0, "未設定");

    private final int index;
    private final String caption;

    AddressUseType(int index, String caption) {
        this.index = index;
        this.caption = caption;
    }

}
