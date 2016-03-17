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
import java.util.*;

@Service
public class MetricService {
    private static final Logger LOG = LoggerFactory.getLogger(MetricService.class);

    private static final String METRICS_INFO_FILE = "metrics-info";
    private Set<String> appIds = new HashSet<>();
    private List<Metric> metrics = new ArrayList<>();
    private Map<String, List<Metric>> appId2metric = new HashMap<>();

    @PostConstruct
    public void initialize() {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                MetricService.class.getClassLoader().getResourceAsStream(METRICS_INFO_FILE)));
        try {
            String line;
            while((line = in.readLine()) != null) {
                String [] items = line.split("\t");
                appIds.add(items[0]);
                if(appId2metric.get(items[0]) == null){
                    appId2metric.put(items[0], new ArrayList<Metric>());
                }
                Metric metric = new Metric(items[0], items[1]);
                appId2metric.get(items[0]).add(metric);
                metrics.add(metric);
            }
            LOG.debug("Successfully read out metrics info from file {}", METRICS_INFO_FILE);
        }catch(IOException e){
            LOG.error("Failed to read out metrics info from file {}", METRICS_INFO_FILE);
        }
    }

    public Set<String> getMetricAppIds(){
        return this.appIds;
    }

    public List<Metric> getMetrics(){
        return metrics;
    }

    public List<Metric> getMetricByAppId(String appId){
        return appId2metric.get(appId);
    }
}
