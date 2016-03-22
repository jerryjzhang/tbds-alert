package com.tencent.tbds.alert.dto;

import com.tencent.tbds.alert.domain.AlertTrigger;
import org.springframework.data.domain.Page;

/**
 * Created by jerryjzhang on 2016/3/21.
 */
public class GetTriggerHistoryResult {
    private Page<AlertTrigger> triggers;

    public GetTriggerHistoryResult(Page<AlertTrigger> triggers){
        this.triggers = triggers;
    }

    public Page<AlertTrigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(Page<AlertTrigger> triggers) {
        this.triggers = triggers;
    }
}
