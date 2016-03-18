package com.tencent.tbds.alert.controller;

import com.tencent.tbds.alert.domain.Alert;
import com.tencent.tbds.alert.dto.GetAlertsResult;
import com.tencent.tbds.alert.service.AlertService;
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
    public String saveAlert(@RequestBody Alert alert){
        alertService.saveAlert(alert);
        return alert.getId();
    }

    @RequestMapping(method= RequestMethod.GET)
    public GetAlertsResult getAlerts(){
        return new GetAlertsResult(alertService.getAlerts());
    }

    @RequestMapping(value="/{alertId}" ,method= RequestMethod.DELETE)
    public void deleteAlert(@PathVariable String alertId){
        alertService.deleteAlert(alertId);
    }
}
