package com.tencent.tbds.alert.domain;

import java.sql.Timestamp;

/**
 * Created by jerryjzhang on 2016/3/22.
 */
public class AlertTriggerQueryCriteria {
    private String metricName;
    private String appId;
    private Long startTime;
    private Long endTime;
    private String read;
    private String keyword;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String isRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
