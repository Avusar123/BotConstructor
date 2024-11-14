package com.botconstructor.hosting.context;

import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.hosting.resolver.GenericResolver;
import com.botconstructor.model.middleware.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MiddlewareContextFactory {

    @Autowired
    private GenericResolver resolver;

    private List<Middleware> middlewares;

    private Provider<?> provider;

    public MiddlewareContextFactory withMiddlewares(List<Middleware> middlewares) {
        this.middlewares = middlewares;

        return this;
    }

    public MiddlewareContextFactory withProvider(Provider<?> provider) {
        this.provider = provider;

        return this;
    }

    public MiddlewareContext build() {
        if (provider == null || middlewares == null) {
            throw new NullPointerException("Не заданы нужные параметры для создания контекста!");
        }

        return new MiddlewareContext(resolver, middlewares, new MiddlewareContextData(), provider);
    }
}
