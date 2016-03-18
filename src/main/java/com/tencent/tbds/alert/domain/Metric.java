package com.tencent.tbds.alert.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jerryjzhang on 2016/3/15.
 */
public class Metric {
    @ApiModelProperty(notes = "告警指标的类别")
    private String appId;
    @ApiModelProperty(notes = "告警指标的名称")
    private String name;
    @ApiModelProperty(notes = "告警指标的别名")
    private String alias;
    @ApiModelProperty(notes = "告警指标的描述")
    private String desc;
    @ApiModelProperty(notes = "告警指标值单位")
    private MetricUnit unit = MetricUnit.COUNT;

    public Metric(String appId, String name) {
        this.appId = appId;
        this.name = name;
    }

    public Metric() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public MetricUnit getUnit() {
        return unit;
    }

    public void setUnit(MetricUnit unit) {
        this.unit = unit;
    }

    public enum MetricUnit {
        PERCENT,
        COUNT;
    }
}
