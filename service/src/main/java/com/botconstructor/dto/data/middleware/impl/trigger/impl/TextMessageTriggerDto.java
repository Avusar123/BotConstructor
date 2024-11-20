package com.botconstructor.dto.data.middleware.impl.trigger.impl;

import com.botconstructor.dto.data.middleware.impl.trigger.TriggerDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class TextMessageTriggerDto extends TriggerDto {
    @JsonProperty("regex")
    @NotBlank
    private String regex;

    @JsonCreator
    protected TextMessageTriggerDto(
            @JsonProperty(value = "regex", required = true) String regex,
            @JsonProperty(value = "order", required = true) int order,
            @JsonProperty(value = "name", required = true) String name) {
        super(order, name);
        this.regex = regex;
    }

    public TextMessageTriggerDto(int order, int id, String name, String regex) {
        super(order, id, name);
        this.regex = regex;
    }


    @Override
    public String getSubType() {
        return "text-message";
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
