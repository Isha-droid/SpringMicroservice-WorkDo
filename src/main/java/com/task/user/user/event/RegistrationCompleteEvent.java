package com.task.user.user.event;

import org.springframework.context.ApplicationEvent;

import com.task.user.user.entity.User;

public class RegistrationCompleteEvent extends ApplicationEvent {
    private final User user;
    private final String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }

    public User getUser() {
        return user;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }
}
