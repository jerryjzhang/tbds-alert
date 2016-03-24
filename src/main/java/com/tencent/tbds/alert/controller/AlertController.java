package com.tencent.tbds.alert.controller;

import com.tencent.tbds.alert.domain.Alert;
import com.tencent.tbds.alert.domain.AlertQueryCriteria;
import com.tencent.tbds.alert.dto.GetAlertsResult;
import com.tencent.tbds.alert.dto.GetTriggerHistoryResult;
import com.tencent.tbds.alert.service.AlertService;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
@RestController
@RequestMapping(value="/alerts")
public class AlertController {
    @Autowired
    private AlertService alertService;

    @RequestMapping(method= RequestMethod.POST)
    @ApiOperation(value = "创建告警或保存修改的告警", notes = "返回对象是告警的ID", response = String.class)
    public Object saveAlert(@RequestBody Alert alert){
        alertService.saveAlert(alert);
        return alert.getId();
    }

    @RequestMapping(method= RequestMethod.GET)
    @ApiOperation(value = "获取所有的告警，有分页机制", notes = "get all alerts", response = GetAlertsResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "每页的大小", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
    })
    public Object getAlerts(Pageable pageable){
        return new GetAlertsResult(alertService.getAlerts(pageable));
    }

    @RequestMapping(value = "/query", method= RequestMethod.GET)
    @ApiOperation(value = "获取所有的告警，有分页机制", notes = "get all alerts", response = GetAlertsResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "指标类别", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "metricName", value = "指标名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "最后修改起始时间", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "endTime", value = "最后修改结束时间", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "keyword", value = "关键字查询，告警名称/指标名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "size", value = "每页的大小", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "第几页", paramType = "query", dataType = "int"),
    })
    public Object queryAlerts(AlertQueryCriteria criteria, Pageable pageable){
        return new GetAlertsResult(alertService.getAlerts(criteria, pageable));
    }

    @RequestMapping(value="/{alertId}", method= RequestMethod.GET)
    @ApiOperation(value = "根据ID获取告警", notes = "get an alert by id", response = Alert.class)
    public Object getAlert(@PathVariable String alertId){
        return alertService.getAlert(alertId);
    }

    @RequestMapping(value="/{alertId}", method= RequestMethod.DELETE)
    @ApiOperation(value = "根据ID删除告警", notes = "delete an alert by id")
    public Object deleteAlert(@PathVariable String alertId){
        alertService.deleteAlert(alertId);
        return true;
    }
}
