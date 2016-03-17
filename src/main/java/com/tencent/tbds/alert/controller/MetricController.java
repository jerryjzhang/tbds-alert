package com.tencent.tbds.alert.controller;

import com.tencent.tbds.alert.dto.GetMetricAppIdsResult;
import com.tencent.tbds.alert.dto.GetMetricsResult;
import com.tencent.tbds.alert.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
@RestController
@RequestMapping(value="/metrics")
public class MetricController {
    @Autowired
    private MetricService metricService;

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public String test() {
        return "hello world";
    }

    @RequestMapping(value="/appids", method= RequestMethod.GET)
    public GetMetricAppIdsResult getMetricAppIds(){
        return new GetMetricAppIdsResult(metricService.getMetricAppIds());
    }

    @RequestMapping(value="/{appid}", method= RequestMethod.GET)
    public GetMetricsResult getMetrcsByAppid(@PathVariable String appid){
        return new GetMetricsResult(metricService.getMetricByAppId(appid));
    }

    @RequestMapping(method=RequestMethod.GET)
    public GetMetricsResult getMetrics(){
        return new GetMetricsResult(metricService.getMetrics());
    }

}