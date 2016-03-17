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
import java.util.Set;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AlertMain.class)
public class TestMetricService {
    @Autowired
    private MetricService metricService;

    @Test
    public void testGetMetricAppIds() {
        Set<String> metrics = metricService.getMetricAppIds();
        assertTrue(metrics.size() > 0);
    }
}
