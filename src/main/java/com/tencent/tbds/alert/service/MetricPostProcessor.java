package com.tencent.tbds.alert.service;

/**
 * Created by jerryjzhang on 2016/3/21.
 */
public interface MetricPostProcessor {
    double process(String metricName, double metricValue);
}
