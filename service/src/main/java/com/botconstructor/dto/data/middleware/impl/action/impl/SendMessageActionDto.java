package com.botconstructor.dto.data.middleware.impl.action.impl;

import com.botconstructor.dto.data.middleware.impl.action.ActionDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class SendMessageActionDto extends ActionDto {
    @JsonProperty("message")
    @NotBlank
    private String message;

    @JsonProperty("chatId")
    @NotBlank
    private String chatId;

    @JsonCreator
    protected SendMessageActionDto(@JsonProperty(value = "message", required = true) String message,
                                   @JsonProperty(value = "chatId", required = true) String chatId,
                                   @JsonProperty(value = "order", required = true) int order,
                                   @JsonProperty(value = "name", required = true) String name) {
        super(order, name);
        this.message = message;
        this.chatId = chatId;
    }

    public SendMessageActionDto(int order, UUID id, String name, String message, String chatId) {
        super(order, id, name);
        this.message = message;
        this.chatId = chatId;
    }

    @Override
    public String getSubType() {
        return "send-message";
    }

    public @NotBlank String getMessage() {
        return message;
    }

    public void setMessage(@NotBlank String message) {
        this.message = message;
    }

    public @NotBlank String getChatId() {
        return chatId;
    }

    public void setChatId(@NotBlank String chatId) {
        this.chatId = chatId;
    }
}
