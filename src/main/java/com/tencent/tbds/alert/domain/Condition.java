package com.tencent.tbds.alert.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
@Entity
@Table(name = "condition")
public class Condition {
    @Id
    @Column(length = 36)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String metricName;

    @OneToOne
    @JsonIgnore
    private Alert alert;

    @Column(length = 5)
    private ConditionRelation relation;

    private Double threshold;

    private int period;

    @Column(length = 20)
    private Statistic statistic = Statistic.Maximum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
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
