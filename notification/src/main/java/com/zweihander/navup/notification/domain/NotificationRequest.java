package com.zweihander.navup.notification.domain;

/**
 * Created by siphokazi on 2017/04/22.
 */
public class NotificationRequest {

    String message;
    String noticeType;
    String userId;

    public void setMessage(String message_){
        this.message = message_;
    }
    public String getMessage(){
        return this.message;
    }


    public void setNoticeType(String notice_){
        this.noticeType = notice_;
    }
    public String getNoticeType(){
        return this.noticeType;
    }

    public void setUserId(String userId_){
        this.userId = userId_;
    }
    public String getUserId(){
        return this.userId;
    }



}
