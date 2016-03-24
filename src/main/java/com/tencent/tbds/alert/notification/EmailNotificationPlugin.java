package com.tencent.tbds.alert.notification;

import com.tencent.tbds.alert.domain.Alert;
import com.tencent.tbds.alert.domain.AlertTrigger;
import com.tencent.tbds.alert.domain.Notification;
import com.tencent.tbds.alert.domain.NotificationContext;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by jerryjzhang on 2016/3/23.
 */
@Service("EMAIL")
public class EmailNotificationPlugin implements NotificationPlugin{
    private static final Logger LOG = LoggerFactory.getLogger(EmailNotificationPlugin.class);

    @Value("${mail.smtp.host}")
    private String smtp_host;
    @Value("${mail.smtp.port}")
    private String smtp_port;
    @Value("${mail.user}")
    private String mail_user;
    @Value("${mail.password}")
    private String mail_password;
    @Value("${mail.sender}")
    private String mail_sender;

    @Override
    public void onAlert(NotificationContext context) {
        if(smtp_host == null || smtp_port == null
                || mail_user == null || mail_password == null){
            LOG.error("Mail account info is not complete, just skip.");
            return;
        }

        Notification notification = context.getNotification();
        Alert alert = context.getAlert();
        AlertTrigger trigger = context.getAlertTrigger();
        try {
            MultiPartEmail email = new MultiPartEmail();

            email.setHostName(smtp_host);
            email.setSmtpPort(Integer.valueOf(smtp_port));
            email.setSSL(true);
            email.setAuthentication(mail_user, mail_password);//邮件服务器验证：用户名/密码
            email.setCharset("UTF-8");

            email.setFrom(mail_user, mail_sender);
            for(String recipient : notification.getRecipients()) {
                email.addTo(recipient);
            }

            email.setSubject("Alert Triggered: " + alert.getName());
            email.setMsg(trigger.getCause());
            email.send();
            LOG.info("Successfully sent notification mail for alert={0}", alert.getId());
        }catch(EmailException e){
            LOG.error("Failed to  notification mail for alert={0}, due to {1}", alert.getId(), e.getMessage());
        }
    }
}
