package com.botconstructor.contract.eventserializer;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.model.event.Event;

public interface EventSerializer<T extends Event> {
    void serialize(T event, MiddlewareContext context);
}
