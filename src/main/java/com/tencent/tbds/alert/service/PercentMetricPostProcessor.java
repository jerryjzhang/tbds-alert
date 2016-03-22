package com.tencent.tbds.alert.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/21.
 */
@Service
public class PercentMetricPostProcessor implements MetricPostProcessor {
    private static final List<String> percentMetricNames = Arrays.asList("cpu_idle","cpu_user", "cpu_system", "cpu_nice");

    @Override
    public double process(String metricName, double metricValue) {
        if(percentMetricNames.contains(metricName)){
            metricValue /= 100;
        }

        return metricValue;
    }
}
