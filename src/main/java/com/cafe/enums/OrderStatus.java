package com.cafe.enums;

import java.util.Arrays;
import java.util.Optional;

public enum OrderStatus {

    PENDING(1),
    FINISHED(2),
    CANCELED(3);

    private final int mValue;

    OrderStatus(int value) {
        mValue = value;
    }

    public static Optional<OrderStatus> valueOf(int value) {
        return Arrays.stream(values())
                .filter(orderStatus -> orderStatus.mValue == value)
                .findFirst();
    }

    public int getInt() {
        return mValue;
    }
}