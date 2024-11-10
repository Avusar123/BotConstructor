package com.botconstructor.contract.context;

import com.botconstructor.contract.resolver.HandlerResolver;
import com.botconstructor.model.middleware.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MiddlewareContextFactory {

    @Autowired
    private HandlerResolver resolver;

    private List<Middleware> middlewares;

    public MiddlewareContextFactory withMiddlewares(List<Middleware> middlewares) {
        this.middlewares = middlewares;

        return this;
    }

    public MiddlewareContext build() {
        return new MiddlewareContext(resolver, middlewares);
    }
}
