package com.cafe.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ProductStatus {

    AVAILABLE(1),
    NOT_AVAILABLE(2);

    private final int mValue;

    ProductStatus(int value) {
        mValue = value;
    }

    public static Optional<ProductStatus> valueOf(int value) {
        return Arrays.stream(values())
                .filter(productStatus -> productStatus.mValue == value)
                .findFirst();
    }

    public int getInt() {
        return mValue;
    }

}