package com.tencent.tbds.alert.domain;

/**
 * Created by jerryjzhang on 2016/3/23.
 */
public class NotificationContext {
    private Alert alert;
    private Notification notification;
    private AlertTrigger alertTrigger;

    public NotificationContext(Alert alert, Notification notification, AlertTrigger alertTrigger) {
        this.alert = alert;
        this.notification = notification;
        this.alertTrigger = alertTrigger;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public AlertTrigger getAlertTrigger() {
        return alertTrigger;
    }

    public void setAlertTrigger(AlertTrigger alertTrigger) {
        this.alertTrigger = alertTrigger;
    }
}
