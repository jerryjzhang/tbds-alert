package com.tencent.tbds.alert.dto;

import com.tencent.tbds.alert.domain.Alert;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/17.
 */
public class GetAlertsResult {
    Page<Alert> alerts;

    public GetAlertsResult(Page<Alert> alerts) {
        this.alerts = alerts;
    }

    public Page<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(Page<Alert> alerts) {
        this.alerts = alerts;
    }
}
