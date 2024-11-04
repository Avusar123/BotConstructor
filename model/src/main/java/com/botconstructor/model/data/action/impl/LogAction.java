package com.botconstructor.model.data.action.impl;

import com.botconstructor.model.data.action.Action;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LogAction extends Action {

    private final String logMessage;

    public LogAction(String name, String logMessage) {
        super(name);
        this.logMessage = logMessage;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public List<Action> getActions() {
        return super.getActions();
    }

    public String getLogMessage() {
        return logMessage;
    }
}
