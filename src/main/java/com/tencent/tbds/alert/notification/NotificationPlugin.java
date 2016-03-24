package com.tencent.tbds.alert.notification;

import com.tencent.tbds.alert.domain.NotificationContext;

/**
 * Created by jerryjzhang on 2016/3/23.
 */
public interface NotificationPlugin {
    void onAlert(NotificationContext context);
}
