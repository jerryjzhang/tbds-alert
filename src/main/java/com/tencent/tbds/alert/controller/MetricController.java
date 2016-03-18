package com.tencent.tbds.alert.controller;

import com.tencent.tbds.alert.dto.GetMetricAppIdsResult;
import com.tencent.tbds.alert.dto.GetMetricsResult;
import com.tencent.tbds.alert.service.MetricService;
import io.swagger.annotations.ApiOperation;
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

    @RequestMapping(value="/appids", method= RequestMethod.GET)
    @ApiOperation(value = "get all metric categories", notes = "get all metric categories")
    public GetMetricAppIdsResult getMetricAppIds(){
        return new GetMetricAppIdsResult(metricService.getMetricAppIds());
    }

    @RequestMapping(value="/{appid}", method= RequestMethod.GET)
    @ApiOperation(value = "get all metrics of given category", notes = "get all metrics of given category")
    public GetMetricsResult getMetrcsByAppid(@PathVariable String appid){
        return new GetMetricsResult(metricService.getMetricByAppId(appid));
    }

    @RequestMapping(method=RequestMethod.GET)
    @ApiOperation(value = "get all metrics", notes = "get all metrics")
    public GetMetricsResult getMetrics(){
        return new GetMetricsResult(metricService.getMetrics());
    }

}