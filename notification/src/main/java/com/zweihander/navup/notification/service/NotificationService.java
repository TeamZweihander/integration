package com.zweihander.navup.notification.service;

import com.zweihander.navup.notification.domain.NotificationRequest;

/**
 * Created by siphokazi on 2017/04/22.
 */
/* TODO: Change return types - must not be void */
public class NotificationService {

    public void logMessageSent(NotificationRequest notificationRequest){
    }

    public void sendEmail(NotificationRequest notificationRequest){
        logMessageSent(notificationRequest);
    }

    public void  sendSMS(NotificationRequest notificationRequest){
        logMessageSent(notificationRequest);
    }
}
