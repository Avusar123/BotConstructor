package com.botconstructor.model.middleware.impl.trigger.impl;

import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.middleware.impl.trigger.Trigger;
import com.botconstructor.model.utils.ShouldFormat;

public class EqualityTrigger extends Trigger {
    @ShouldFormat
    private String firstValue;

    @ShouldFormat
    private String lastValue;

    private boolean inverted;


    public EqualityTrigger(int order, String firstValue, String lastValue, boolean inverted) {
        super(order);
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
}
