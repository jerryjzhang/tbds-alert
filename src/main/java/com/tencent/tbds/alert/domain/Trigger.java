package com.tencent.tbds.alert.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by jerryjzhang on 2016/3/17.
 */
@Entity
@Table(name = "trigger")
public class Trigger {
    @Id
    @Column(length = 36)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String alertId;
    private Timestamp time;

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}