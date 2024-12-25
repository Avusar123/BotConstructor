package com.botconstructor.dto.data.middleware.impl.trigger.impl;

import com.botconstructor.dto.data.middleware.impl.trigger.TriggerDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public class EqualityTriggerDto extends TriggerDto {
    @JsonProperty(value = "first")
    @NotEmpty
    private String firstValue;

    @JsonProperty(value = "last")
    @NotEmpty
    private String lastValue;

    @JsonProperty("inverted")
    private boolean inverted;

    @JsonCreator
    protected EqualityTriggerDto(@JsonProperty(value = "first", required = true) String firstValue,
                                 @JsonProperty(value = "last", required = true) String lastValue,
                                 @JsonProperty("inverted") boolean inverted,
                                 @JsonProperty(value = "order", required = true) int order,
                                 @JsonProperty(value = "name", required = true) String name) {
        super(order, name);
        this.firstValue = firstValue;
        this.lastValue = lastValue;
        this.inverted = inverted;
    }

    public EqualityTriggerDto(int order,
                              UUID id,
                              String firstValue,
                              String lastValue,
                              String name,
                              boolean inverted) {
        super(order, id, name);
        this.firstValue = firstValue;
        this.lastValue = lastValue;
        this.inverted = inverted;
    }


    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getLastValue() {
        return lastValue;
    }

    public void setLastValue(String lastValue) {
        this.lastValue = lastValue;
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    @Override
    public String getSubType() {
        return "equality";
    }
}
