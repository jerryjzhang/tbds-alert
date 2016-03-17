package com.tencent.tbds.alert.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by jerryjzhang on 2016/3/16.
 */
public class GetMetricAppIdsResult implements Serializable{
    private Set<String> appIds;

    public GetMetricAppIdsResult(Set<String> appIds) {
        this.appIds = appIds;
    }

    public Set<String> getAppIds() {
        return appIds;
    }

    public void setAppIds(Set<String> appIds) {
        this.appIds = appIds;
    }
}
