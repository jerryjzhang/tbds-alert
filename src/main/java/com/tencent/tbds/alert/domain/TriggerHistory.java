package com.tencent.tbds.alert.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by jerryjzhang on 2016/3/17.
 */
@Entity
@Table(name = "trigger_history")
public class TriggerHistory {
    @Id
    @Column(length = 36)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @ApiModelProperty(notes = "告警ID")
    private String alertId;

    @ApiModelProperty(notes = "告警指标的类别")
    private String appId;

    @ApiModelProperty(notes = "告警指标的名称")
    private String metricName;

    @ApiModelProperty(dataType = "java.lang.Long", notes = "告警触发时间")
    private Timestamp time;

    @ApiModelProperty(notes = "告警触发的原因")
    private String cause;

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
