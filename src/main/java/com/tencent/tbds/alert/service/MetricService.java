package com.tencent.tbds.alert.service;

/**
 * Created by jerryjzhang on 2016/3/16.
 */

import com.tencent.tbds.alert.domain.Metric;
import com.tencent.tbds.alert.domain.Statistic;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private Map<String, Metric> name2metric = new HashMap<>();

    @Value("${environment}")
    private String environment;
    private boolean isDEV;
    private Random rand = new Random();

    @Value("${influxdb.url}")
    private String influxdb_url;
    @Value("${influxdb.user}")
    private String influxdb_user;
    @Value("${influxdb.password}")
    private String influxdb_password;
    @Value("${influxdb.database}")
    private String influxdb_database;
    private InfluxDB influxDB;

    @Autowired
    private List<MetricPostProcessor> metricPostProcessors;

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
                name2metric.put(items[0] + items[1], metric);
                metrics.add(metric);
            }
            LOG.debug("Successfully read out metrics info from file {}", METRICS_INFO_FILE);
        }catch(IOException e){
            LOG.error("Failed to read out metrics info from file {}", METRICS_INFO_FILE);
        }

        if("DEV".equals(environment)){
            isDEV = true;
        }else{
            influxDB = InfluxDBFactory.connect(influxdb_url, influxdb_user, influxdb_password);
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

    public Metric getMetric(String appId, String name){
        return name2metric.get(appId + name);
    }

    public double getMetricValue(String appId, String metricName, int period, Statistic statistic){
        if(isDEV){
            return rand.nextDouble() * 100;
        }else{
            double value = 0.0;
            String influxdb_sql = String.format("SELECT %s(\"value\") FROM \"%s\" WHERE time > now() - %dm",
                    statistic, appId + "_" + metricName, period);
            QueryResult rs = influxDB.query(new Query(influxdb_sql, influxdb_database));
            for(QueryResult.Result r : rs.getResults()){
                for(QueryResult.Series s : r.getSeries()){
                    Iterator<List<Object> > itr = s.getValues().iterator();
                    while(itr.hasNext()){
                        value = (double)itr.next().get(1);
                        for(MetricPostProcessor processor : metricPostProcessors){
                            value = processor.process(metricName, value);
                        }
                    }
                }
            }

            return value;
        }
    }
}
