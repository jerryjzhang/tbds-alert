package com.tencent.tbds.alert.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
@Entity
@Table(name = "alert_condition")
public class Condition {
    @Id
    @Column(length = 36)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(notes = "告警触发条件ID，新建告警不需要填写")
    private String id;

    @ApiModelProperty(notes = "告警指标的类别", required = true)
    private String appId;

    @ApiModelProperty(notes = "告警指标的名称", required = true)
    private String metricName;

    @OneToOne
    @JsonIgnore
    private Alert alert;

    @Column(name = "frelation",length = 5)
    @ApiModelProperty(notes = "告警阈值与指标值的比较关系")
    private ConditionRelation relation;

    @ApiModelProperty(notes = "告警阈值")
    private Double threshold;

    @ApiModelProperty(notes = "告警探测周期")
    private int period;

    @Column(length = 20)
    @ApiModelProperty(notes = "告警指标值统计类型")
    private Statistic statistic = Statistic.Max;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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
