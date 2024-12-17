package com.botconstructor.dto.data.middleware.impl.action;

import com.botconstructor.dto.data.middleware.MiddlewareDto;

import java.util.UUID;

public abstract class ActionDto extends MiddlewareDto {
    public ActionDto(int order, UUID id, String name) {
        super(order, id, name);
    }

    public ActionDto(int order, String name) {
        super(order, name);
    }

    @Override
    public String getType() {
        return "action";
    }
}
