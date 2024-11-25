package com.botconstructor.dto.data.middleware.impl.trigger.impl;

import com.botconstructor.dto.data.middleware.impl.trigger.TriggerDto;
import com.botconstructor.model.utils.ShouldFormat;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class CommandMessageTriggerDto extends TriggerDto {
    @JsonProperty("commandName")
    @NotBlank
    private String commandName;

    @JsonProperty("maxParams")
    private int maxParamsCount;

    @JsonProperty("variable")
    @NotBlank
    private String variableToSave;

    public CommandMessageTriggerDto(int order, int id, String name, String commandName, int maxParamsCount, String variableToSave) {
        super(order, id, name);
        this.commandName = commandName;
        this.maxParamsCount = maxParamsCount;
        this.variableToSave = variableToSave;
    }

    @Override
    public String getSubType() {
        return "command";
    }

    @JsonCreator
    protected CommandMessageTriggerDto(@JsonProperty(value = "commandName", required = true) String commandName,
                                 @JsonProperty(value = "maxParams", required = true) int maxParams,
                                 @JsonProperty(value = "variable", required = true) String variableToSave,
                                 @JsonProperty(value = "order", required = true) int order,
                                 @JsonProperty(value = "name", required = true) String name) {
        super(order, name);
        this.commandName = commandName;
        this.maxParamsCount = maxParams;
        this.variableToSave = variableToSave;
    }

    public String getVariableToSave() {
        return variableToSave;
    }

    public void setVariableToSave(String variableToSave) {
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
}
