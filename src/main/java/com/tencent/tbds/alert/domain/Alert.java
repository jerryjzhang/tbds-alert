package com.tencent.tbds.alert.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

/**
 * Created by jerryjzhang on 2016/3/15.
 */
@Entity
@Table(name = "alert")
public class Alert {
    @Id
    @Column(length = 36)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String name;
    private String desc;
    private String lastUpdateBy;
    private int interval;

    @ApiModelProperty(dataType = "java.lang.Long")
    private Timestamp lastUpdateTime;

    @ApiModelProperty(dataType = "java.lang.Long")
    private Timestamp lastTriggerTime;

    @OneToOne(cascade = CascadeType.ALL)
    private Condition condition;

    @OneToMany(mappedBy = "alert", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Notification> notifications;

    private boolean enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Timestamp getLastTriggerTime() {
        return lastTriggerTime;
    }

    public void setLastTriggerTime(Timestamp lastTriggerTime) {
        this.lastTriggerTime = lastTriggerTime;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
