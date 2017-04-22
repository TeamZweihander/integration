package com.zweihander.navup.notification.controllers;

import java.util.LinkedList;
import java.util.List;

import com.zweihander.navup.notification.domain.NotificationRequest;
import com.zweihander.navup.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
/* TODO: Add validation to ensure user info is added in request */
public class NotificationController
{
    private final NotificationService notificationService = new NotificationService();

    @RequestMapping(value ="/sendEmailNotification", method = RequestMethod.POST)
    public ResponseEntity sendEmailNotification(NotificationRequest request) {
        notificationService.sendEmail(request);
        return ResponseEntity.ok("Email Notification sent");
    }

    @RequestMapping(value ="/sendSMSNotification", method = RequestMethod.POST)
    public ResponseEntity sendSMSNotification(NotificationRequest request) {
        notificationService.sendSMS(request);
        return ResponseEntity.ok("SMS Notification sent");
    }

}
