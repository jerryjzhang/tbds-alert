package com.tencent.tbds.alert.domain;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
public enum Statistic {
    Mean("Mean"),
    Sum("Sum"),
    Min("Min"),
    Max("Max");

    private String value;

    private Statistic(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Use this in place of valueOf.
     *
     * @param value
     *        real value
     * @return Statistic corresponding to the value
     */
    public static Statistic fromValue(String value) {
        if (value == null || "".equals(value)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if ("Mean".equals(value)) {
            return Mean;
        } else if ("Sum".equals(value)) {
            return Sum;
        } else if ("Min".equals(value)) {
            return Min;
        } else if ("Max".equals(value)) {
            return Max;
        } else {
            throw new IllegalArgumentException("Cannot create enum from "
                    + value + " value!");
        }
    }
}
