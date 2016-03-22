package com.tencent.tbds.alert.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    @ApiModelProperty(notes = "告警ID，新建告警不需要填写")
    private String id;

    @ApiModelProperty(notes = "告警名称", required = true)
    private String name;

    @ApiModelProperty(notes = "告警描述")
    private String desc;

    @ApiModelProperty(notes = "告警最后修改人", required = true)
    private String lastUpdateBy;

    @ApiModelProperty(notes = "告警频率/间隔", required = true)
    private int interval;

    @ApiModelProperty(dataType = "java.lang.Long", notes = "告警最后修改时间，新创建的告警不需要填写")
    private Timestamp lastUpdateTime;

    @ApiModelProperty(dataType = "java.lang.Long", notes = "告警最后触发时间，新创建的告警不需要填写")
    private Timestamp lastTriggerTime;

    @OneToOne(cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "告警触发条件", required = true)
    private Condition condition;

    @OneToMany(mappedBy = "alert", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ApiModelProperty(notes = "告警通知对象")
    private List<Notification> notifications = new ArrayList<>();

    @ApiModelProperty(notes = "是否开启告警")
    private boolean enabled = true;

    @ApiModelProperty(notes = "当前状态")
    private AlertState state = AlertState.Normal;

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

    public AlertState getState() {
        return state;
    }

    public void setState(AlertState state) {
        this.state = state;
    }

    public enum AlertState {
        Normal,
        Abnormal
    }
}
