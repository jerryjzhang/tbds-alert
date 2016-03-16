package com.tencent.tbds.alert.controller;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
@RestController
@RequestMapping(value="/metric")
public class MetricController {
    @Value("${influxdb.url}")
    private String influxdbURL;

    @Value("${influxdb.user}")
    private String influxdbUser;

    @Value("${influxdb.password}")
    private String influxdbPassword;

    @Value("${influxdb.database}")
    private String influxdbDatabase;

    @RequestMapping(value="/{user}", method= RequestMethod.GET)
    public String getUser(@PathVariable String user) {
        return user;
    }

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public String test() {
        InfluxDB influxDB = InfluxDBFactory.connect(influxdbURL, influxdbUser, influxdbPassword);
        QueryResult rs = influxDB.query(new Query("SHOW MEASUREMENTS", influxdbDatabase));
        StringBuilder sb = new StringBuilder();
        for(QueryResult.Result r : rs.getResults()){
            sb.append(r.getSeries());
            sb.append("\n");
        }

        return sb.toString();
    }
}