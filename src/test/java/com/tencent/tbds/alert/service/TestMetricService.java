package com.tencent.tbds.alert.service;

import com.tencent.tbds.alert.AlertMain;
import com.tencent.tbds.alert.domain.Metric;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AlertMain.class)
public class TestMetricService {
    @Autowired
    private MetricService metricService;

    @Test
    public void testGetMetrics() {
        List<Metric> metrics = metricService.getMetrics();
        assertTrue(metrics.size() > 0);
    }
}
