package com.zweihander.navup.notification.service.request;

import com.zweihander.navup.notification.Request;
import com.zweihander.navup.notification.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Nsovo on 2017/03/28.
 */
public class SendEmailRequest extends Request{

    private static final long serialVersionUID = 3217162401112396608L;

    private User user;

    private String subject;

    private String message;

    private boolean isMultipart;

    private boolean isHtml;

    public SendEmailRequest(User user, String subject, String message, boolean isMultipart, boolean isHtml) {
        this.user = user;
        this.subject = subject;
        this.message = message;
        this.isMultipart = isMultipart;
        this.isHtml = isHtml;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMultipart() {
        return isMultipart;
    }

    public void setMultipart(boolean multipart) {
        isMultipart = multipart;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean html) {
        isHtml = html;
    }
}
