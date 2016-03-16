package com.tencent.tbds.alert.dto;

import com.tencent.tbds.alert.domain.Metric;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
public class GetMetricsResult implements Serializable{
    private List<Metric> metrics;

    public GetMetricsResult(List<Metric> metrics){
        this.metrics = metrics;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }
}
