package com.tencent.tbds.alert.domain;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
public enum Statistic {
    Average("Mean"),
    Sum("Sum"),
    Minimum("Min"),
    Maximum("Max");

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
            return Average;
        } else if ("Sum".equals(value)) {
            return Sum;
        } else if ("Min".equals(value)) {
            return Minimum;
        } else if ("Max".equals(value)) {
            return Maximum;
        } else {
            throw new IllegalArgumentException("Cannot create enum from "
                    + value + " value!");
        }
    }
}
