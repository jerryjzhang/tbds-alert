package com.tencent.tbds.alert.service;

import com.tencent.tbds.alert.dao.AlertTriggerRepository;
import com.tencent.tbds.alert.dao.CustomerSpecifications;
import com.tencent.tbds.alert.domain.AlertTrigger;
import com.tencent.tbds.alert.domain.AlertTriggerQueryCriteria;
import com.tencent.tbds.alert.dto.ResponseStatus;
import com.tencent.tbds.alert.exception.TBDSAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/22.
 */
@Service
public class AlertTriggerService {
    @Autowired
    private AlertTriggerRepository alertTriggerRepository;

    public List<AlertTrigger> getTriggerHistory(int windowSize){
        windowSize = windowSize == 0 ? 30 : windowSize;
        long startTime = System.currentTimeMillis() - windowSize * 1000;
        //return alertTriggerRepository.findAll(CustomerSpecifications.triggerOccursInTimeWindow(new Timestamp(startTime)));
        return alertTriggerRepository.findAll(new Timestamp(startTime));
    }

    public Page<AlertTrigger> getTriggerHistory(AlertTriggerQueryCriteria criteria, Pageable pageable){
        return alertTriggerRepository.findAll(CustomerSpecifications.AlertTriggerQuery(criteria), pageable);
    }

    public void setRead(String id, boolean read){
        AlertTrigger alertTrigger = alertTriggerRepository.findOne(id);
        if(alertTrigger == null){
            throw new TBDSAlertException(ResponseStatus.NOT_FOUND.getCode(), "Alert trigger not found");
        }
        alertTrigger.setRead(read);
        alertTriggerRepository.save(alertTrigger);
        /*
        int row = alertTriggerRepository.setRead(id, read);
        if(row == 0){
            throw new TBDSAlertException(ResponseStatus.NOT_FOUND.getCode(), "Alert trigger not found");
        }

        return row;
        */
    }
}
