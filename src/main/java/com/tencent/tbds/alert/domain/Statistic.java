package com.tencent.tbds.alert.domain;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
public enum Statistic {

    SampleCount("SampleCount"),
    Average("Average"),
    Sum("Sum"),
    Minimum("Minimum"),
    Maximum("Maximum");

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
        } else if ("SampleCount".equals(value)) {
            return SampleCount;
        } else if ("Average".equals(value)) {
            return Average;
        } else if ("Sum".equals(value)) {
            return Sum;
        } else if ("Minimum".equals(value)) {
            return Minimum;
        } else if ("Maximum".equals(value)) {
            return Maximum;
        } else {
            throw new IllegalArgumentException("Cannot create enum from "
                    + value + " value!");
        }
    }
}
