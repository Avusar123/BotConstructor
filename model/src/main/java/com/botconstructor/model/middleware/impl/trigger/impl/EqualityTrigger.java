package com.botconstructor.model.middleware.impl.trigger.impl;

import com.botconstructor.model.middleware.impl.trigger.Trigger;
import com.botconstructor.model.utils.ShouldFormat;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class EqualityTrigger extends Trigger {
    @ShouldFormat
    @NotNull
    private String firstValue;

    @ShouldFormat
    @NotNull
    private String lastValue;

    private boolean inverted;


    public EqualityTrigger(int order,
                           String firstValue,
                           String lastValue,
                           String name,
                           boolean inverted) {
        super(order, name);
        this.firstValue = firstValue;
        this.lastValue = lastValue;
        this.inverted = inverted;
    }

    protected EqualityTrigger() {

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
}
