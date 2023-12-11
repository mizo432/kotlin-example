package undecided.sale.product.model.prefecture;

import lombok.Getter;

import java.util.Objects;

import static java.util.Objects.isNull;

@Getter
public class PrefectureCode {

    private static final undecided.sale.cash.model.prefecture.PrefectureCode EMPTY = new undecided.sale.cash.model.prefecture.PrefectureCode(null);

    private final String value;

    private PrefectureCode(String value) {
        this.value = value;
    }

    public static undecided.sale.cash.model.prefecture.PrefectureCode of(String value) {
        Objects.requireNonNull(value);
        if (value.length() == 2) {
            return new undecided.sale.cash.model.prefecture.PrefectureCode(value);
        }
        throw new IllegalArgumentException("Illegal format");

    }

    public static undecided.sale.cash.model.prefecture.PrefectureCode reconstruct(String value) {
        if (isNull(value)) {
            return undecided.sale.cash.model.prefecture.PrefectureCode.EMPTY;
        }

        return new undecided.sale.cash.model.prefecture.PrefectureCode(value);

    }

    @Override
    public String toString() {
        return value;
    }
}
