package com.tencent.tbds.alert.controller;

import com.tencent.tbds.alert.domain.Alert;
import com.tencent.tbds.alert.dto.GetAlertsResult;
import com.tencent.tbds.alert.service.AlertService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ApiOperation(value = "create or save an alert", notes = "response body contains a unique alert id", response = String.class)
    public String saveAlert(@RequestBody Alert alert){
        alertService.saveAlert(alert);
        return alert.getId();
    }

    @RequestMapping(method= RequestMethod.GET)
    @ApiOperation(value = "get all alerts", notes = "get all alerts")
    public GetAlertsResult getAlerts(){
        return new GetAlertsResult(alertService.getAlerts());
    }

    @RequestMapping(value="/{alertId}", method= RequestMethod.GET)
    @ApiOperation(value = "get an alert by id", notes = "get an alert by id")
    public Alert getAlert(@PathVariable String alertId){
        return alertService.getAlert(alertId);
    }

    @RequestMapping(value="/{alertId}", method= RequestMethod.DELETE)
    @ApiOperation(value = "delete an alert by id", notes = "delete an alert by id")
    public void deleteAlert(@PathVariable String alertId){
        alertService.deleteAlert(alertId);
    }
}
