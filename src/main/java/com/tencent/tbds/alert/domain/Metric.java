package com.tencent.tbds.alert.domain;

/**
 * Created by jerryjzhang on 2016/3/15.
 */
public class Metric {
    private String appId;
    private String name;
    private String alias;
    private String desc;
    private MetricUnit unit;

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
