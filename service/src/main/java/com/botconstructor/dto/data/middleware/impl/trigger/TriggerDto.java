package com.botconstructor.dto.data.middleware.impl.trigger;

import com.botconstructor.dto.data.middleware.MiddlewareDto;


public abstract class TriggerDto extends MiddlewareDto {

    public TriggerDto(int order, int id, String name) {
        super(order, id, name);
    }

    public TriggerDto(int order, String name) {
        super(order, name);
    }

    @Override
    public String getType() {
        return "trigger";
    }
}
