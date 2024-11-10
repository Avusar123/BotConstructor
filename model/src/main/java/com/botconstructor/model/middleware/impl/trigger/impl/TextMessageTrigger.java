package com.botconstructor.model.middleware.impl.trigger.impl;

import com.botconstructor.model.middleware.impl.trigger.Trigger;
import com.botconstructor.model.utils.ShouldFormat;
import jakarta.persistence.Entity;

@Entity
public class TextMessageTrigger extends Trigger {
    @ShouldFormat
    private String regex;

    public TextMessageTrigger(int order, String regex) {
        super(order);
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
