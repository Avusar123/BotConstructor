package com.botconstructor.model.event.impl;

import com.botconstructor.model.event.Event;
import com.botconstructor.model.event.EventType;

public class TextMessageEvent extends Event {
    private String chatId;

    private String message;

    public TextMessageEvent(String chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }

    @Override
    public EventType getType() {
        return EventType.TEXT_MESSAGE;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
