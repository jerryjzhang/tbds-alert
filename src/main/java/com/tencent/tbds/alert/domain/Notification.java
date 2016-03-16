package com.tencent.tbds.alert.domain;

import java.sql.Timestamp;

/**
 * Created by jerryjzhang on 2016/3/15.
 */
public class Notification {
    public enum NotificationType {
        PORTAL,
        HTTP,
        SMS,
        EMAIL,
    }

    private NotificationType type;
    private Timestamp time;

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
