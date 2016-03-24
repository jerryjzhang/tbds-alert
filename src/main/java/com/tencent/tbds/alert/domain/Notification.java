package com.tencent.tbds.alert.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jerryjzhang on 2016/3/15.
 */
@Entity
@Table(name = "alert_notification")
public class Notification {
    public enum NotificationType {
        PORTAL,
        API,
        SMS,
        EMAIL,
        WEICHAT
    }

    @Id
    @Column(length = 36)
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(notes = "告警通知ID，新建告警不需要填写")
    private String id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "alert")
    @JsonIgnore
    private Alert alert;

    @Column(length = 10)
    @ApiModelProperty(notes = "通知推送渠道")
    private NotificationType type = NotificationType.PORTAL;

    @ElementCollection
    @ApiModelProperty(notes = "通知推送接收者")
    private List<String> recipients;

    @ApiModelProperty(dataType = "java.lang.Long", notes = "上次通知推送时间")
    private Timestamp lastPushTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public Timestamp getLastPushTime() {
        return lastPushTime;
    }

    public void setLastPushTime(Timestamp lastPushTime) {
        this.lastPushTime = lastPushTime;
    }
}
