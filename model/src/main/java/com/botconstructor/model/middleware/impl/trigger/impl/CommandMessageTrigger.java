package com.botconstructor.model.middleware.impl.trigger.impl;

import com.botconstructor.model.middleware.impl.trigger.Trigger;
import com.botconstructor.model.utils.ShouldFormat;
import jakarta.persistence.Entity;

@Entity
public class CommandMessageTrigger extends Trigger {
    @ShouldFormat
    private String commandName;

    private int maxParamsCount;

    private String variableToSave;

    public CommandMessageTrigger(
            int order,
            String commandName,
            int maxParamsCount,
            String variableToSave) {
        super(order);
        this.commandName = commandName;
        this.maxParamsCount = maxParamsCount;
        this.variableToSave = variableToSave;
    }

    public int getMaxParamsCount() {
        return maxParamsCount;
    }

    public void setMaxParamsCount(int maxParamsCount) {
        this.maxParamsCount = maxParamsCount;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getVariableToSave() {
        return variableToSave;
    }

    public void setVariableToSave(String variableToSave) {
        this.variableToSave = variableToSave;
    }
}
