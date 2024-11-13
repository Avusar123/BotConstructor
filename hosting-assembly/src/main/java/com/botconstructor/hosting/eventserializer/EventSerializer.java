package com.botconstructor.hosting.eventserializer;

import com.botconstructor.hosting.context.MiddlewareContext;
import com.botconstructor.model.event.Event;

public interface EventSerializer<T extends Event> {
    void serialize(T event, MiddlewareContext context);
}
