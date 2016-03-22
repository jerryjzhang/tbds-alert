package com.tencent.tbds.alert.controller;

import com.tencent.tbds.alert.domain.AlertTriggerQueryCriteria;
import com.tencent.tbds.alert.dto.GetTriggerHistoryResult;
import com.tencent.tbds.alert.service.AlertTriggerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jerryjzhang on 2016/3/22.
 */
@RestController
@RequestMapping(value="/triggers")
public class AlertTriggerController {
    @Autowired
    private AlertTriggerService alertTriggerService;

    @RequestMapping(value="/query", method= RequestMethod.GET)
    @ApiOperation(value = "查询最近触发的告警信息, 分页返回", notes = "分页返回", response = GetTriggerHistoryResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "指标类别", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "metricName", value = "指标名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "read", value = "是否已读, true/false", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "keyword", value = "关键字查询，指标类别/指标名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "pageSize", value = "每页的大小", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageNumber", value = "第几页", paramType = "query", dataType = "int"),
    })
    public Object getTriggers(AlertTriggerQueryCriteria criteria, Pageable pageable){
        return new GetTriggerHistoryResult(alertTriggerService.getTriggerHistory(criteria, pageable));
    }

    @ApiOperation(value = "设置告警触发通知已读/未读", notes = "默认设置为已读", response = Integer.class)
    @ApiImplicitParam(name = "isRead", value = "true-已读，false-未读", paramType = "query", dataType = "string")
    @RequestMapping(value="/read/{alertTriggerId}", method= RequestMethod.PUT)
    public Object markTriggerRead(@PathVariable String alertTriggerId, String read){
        boolean isRead = read == null || "true".equals(read) ? true : false;
        alertTriggerService.setRead(alertTriggerId, isRead);
        return true;
    }
}
