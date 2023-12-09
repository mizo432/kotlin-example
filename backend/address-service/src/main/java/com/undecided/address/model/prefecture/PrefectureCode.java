package com.undecided.address.model.prefecture;

import java.util.Objects;

import static java.util.Objects.isNull;

public class PrefectureCode {

    private static final PrefectureCode EMPTY = new PrefectureCode(null);

    private final String value;


    private PrefectureCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PrefectureCode of(String value) {
        Objects.requireNonNull(value);
        if (value.length() == 2) {
            return new PrefectureCode(value);
        }
        throw new IllegalArgumentException("Illegal format");

    }

    public static PrefectureCode reconstruct(String value) {
        if (isNull(value)) {
            return PrefectureCode.EMPTY;
        }

        return new PrefectureCode(value);

    }

    @Override
    public String toString() {
        return value;
    }
}
