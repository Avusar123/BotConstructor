package com.botconstructor.model.event.impl;

import com.botconstructor.model.event.Event;
import com.botconstructor.model.event.EventType;

public class CommandMessageEvent extends TextMessageEvent {
    private String commandName;

    private String paramsString;

    public CommandMessageEvent(String chatId,
                               String message,
                               String commandName,
                               String paramsString) {
        super(chatId, message);
        this.commandName = commandName;
        this.paramsString = paramsString;
    }

    @Override
    public EventType getType() {
        return EventType.COMMAND_MESSAGE;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getParamsString() {
        return paramsString;
    }

    public void setParamsString(String paramsString) {
        this.paramsString = paramsString;
    }
}
