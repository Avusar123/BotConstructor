package com.botconstructor.contract.resolver;

import com.botconstructor.contract.eventserializer.EventSerializer;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.model.event.Event;
import com.botconstructor.model.middleware.Middleware;

public interface GenericResolver {
    MiddlewareHandler<Middleware> resolveHandler(Middleware middleware);

    EventSerializer<Event> resolveSerializer(Event event);
}
