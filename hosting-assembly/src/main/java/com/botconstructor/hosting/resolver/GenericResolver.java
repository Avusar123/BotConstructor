package com.botconstructor.hosting.resolver;

import com.botconstructor.hosting.eventserializer.EventSerializer;
import com.botconstructor.hosting.handler.MiddlewareHandler;
import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.model.event.Event;
import com.botconstructor.model.middleware.Middleware;

public interface GenericResolver {
    MiddlewareHandler<Middleware> resolveHandler(Middleware middleware);

    EventSerializer<Event> resolveSerializer(Event event);

    Provider<ProviderConfig> resolveProvider(ProviderConfig config);
}
