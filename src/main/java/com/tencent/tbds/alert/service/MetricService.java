package com.tencent.tbds.alert.service;

/**
 * Created by jerryjzhang on 2016/3/16.
 */

import com.tencent.tbds.alert.domain.Metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetricService {
    private static final Logger LOG = LoggerFactory.getLogger(MetricService.class);

    private static final String METRICS_INFO_FILE = "metrics-info";
    private List<Metric> metrics = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                MetricService.class.getClassLoader().getResourceAsStream(METRICS_INFO_FILE)));
        try {
            String line;
            while((line = in.readLine()) != null) {
                String [] items = line.split("\t");
                metrics.add(new Metric(items[0], items[1]));
            }
            LOG.debug("Successfully read out metrics info from file {}", METRICS_INFO_FILE);
        }catch(IOException e){
            LOG.error("Failed to read out metrics info from file {}", METRICS_INFO_FILE);
        }
    }

    public List<Metric> getMetrics(){
        return metrics;
    }
}
