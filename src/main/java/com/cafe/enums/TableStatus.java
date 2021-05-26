package com.cafe.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TableStatus {

    BUSY(1),
    FREE(2),
    RESERVED(3);

    private final int mValue;

    TableStatus(int value) {
        mValue = value;
    }

    public static Optional<TableStatus> valueOf(int value) {
        return Arrays.stream(values())
                .filter(tableStatus -> tableStatus.mValue == value)
                .findFirst();
    }

    public int getInt() {
        return mValue;
    }

}