package com.botconstructor.model.middleware.impl.action.impl;

import com.botconstructor.model.middleware.impl.action.Action;
import com.botconstructor.model.utils.ShouldFormat;
import jakarta.persistence.Entity;

@Entity
public class SendMessageAction extends Action {
    @ShouldFormat
    private String message;

    @ShouldFormat
    private String chatId;

    public SendMessageAction(int order, String message, String chatId) {
        super(order);
        this.message = message;
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
