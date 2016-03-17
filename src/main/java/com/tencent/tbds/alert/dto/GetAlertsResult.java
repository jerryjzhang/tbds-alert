package com.tencent.tbds.alert.dto;

import com.tencent.tbds.alert.domain.Alert;

import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/17.
 */
public class GetAlertsResult {
    List<Alert> alerts;

    public GetAlertsResult(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
