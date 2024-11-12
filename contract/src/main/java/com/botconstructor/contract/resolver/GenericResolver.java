package com.botconstructor.contract.resolver;

import com.botconstructor.contract.eventserializer.EventSerializer;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.model.event.Event;
import com.botconstructor.model.middleware.Middleware;

public interface GenericResolver {
    MiddlewareHandler<Middleware> resolveHandler(Middleware middleware);

    EventSerializer<Event> resolveSerializer(Event event);

    Provider<ProviderConfig> resolveProvider(ProviderConfig config);
}
