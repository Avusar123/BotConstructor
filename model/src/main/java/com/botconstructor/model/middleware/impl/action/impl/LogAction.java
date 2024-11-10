package com.botconstructor.model.middleware.impl.action.impl;

import com.botconstructor.model.middleware.impl.action.Action;
import com.botconstructor.model.utils.ShouldFormat;
import jakarta.persistence.Entity;

@Entity
public class LogAction extends Action {
    @ShouldFormat
    private String message;

    public LogAction(int order, String message) {
        super(order);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}