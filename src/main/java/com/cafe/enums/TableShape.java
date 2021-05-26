package com.cafe.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TableShape {

    ROUND(1),
    SQUARE(2),
    OVAL(3),
    RECTANGLE(4);

    private final int mValue;

    TableShape(int value) {
        mValue = value;
    }

    public static Optional<TableShape> valueOf(int value) {
        return Arrays.stream(values())
                .filter(tableShape -> tableShape.mValue == value)
                .findFirst();
    }

    public int getInt() {
        return mValue;
    }

}
