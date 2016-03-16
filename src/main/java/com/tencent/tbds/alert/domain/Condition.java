package com.tencent.tbds.alert.domain;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
public class Condition {
    private Metric metric;
    private ConditionRelation relation;
    private Double threshold;
    private int period;

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public ConditionRelation getRelation() {
        return relation;
    }

    public void setRelation(ConditionRelation relation) {
        this.relation = relation;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public enum ConditionRelation {
        GT(">"),
        LT("<"),
        EQ("="),
        GTEQ(">="),
        LTEQ("<=");

        private String symbol;
        ConditionRelation(String symbol){
            this.symbol = symbol;
        }

        @Override
        public String toString(){
            return this.symbol;
        }
    }
}
