package com.tencent.tbds.alert.domain;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
public class Condition {
    private Metric metric;
    private ConditionRelation relation;
    private Double threshold;
    private int period;
    private Statistic statistic;

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

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
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

        public static ConditionRelation fromValue(String symbol){
            if (symbol == null || "".equals(symbol)) {
                throw new IllegalArgumentException("Value cannot be null or empty!");
            }

            if(GT.symbol.equals(symbol)){
                return GT;
            }else if(LT.symbol.equals(symbol)){
                return LT;
            }else if(EQ.symbol.equals(symbol)){
                return EQ;
            }else if(GTEQ.symbol.equals(symbol)){
                return GTEQ;
            }else if(LTEQ.symbol.equals(symbol)){
                return LTEQ;
            }

            throw new IllegalArgumentException("Cannot create enum from "
                    + symbol + " value!");
        }
    }
}
