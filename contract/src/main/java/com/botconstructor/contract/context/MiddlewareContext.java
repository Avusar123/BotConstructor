package com.botconstructor.contract.context;

import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.resolver.HandlerResolver;
import com.botconstructor.model.middleware.Middleware;

import java.util.Iterator;
import java.util.List;

public class MiddlewareContext {
    private int next;

    private final HandlerResolver handlerResolver;

    private final List<Middleware> middlewares;

    MiddlewareContext(HandlerResolver resolver, List<Middleware> middlewares) {
        this.handlerResolver = resolver;
        this.middlewares = middlewares;
        reset();
    }

    public void reset() {
        next = 1;
    }

    public boolean hasNext() {
        return next < middlewares.size() + 1;
    }

    public void next() {
        var middleware = middlewares.get(next - 1);
        handlerResolver.resolveHandler(middleware).act(middleware, this);
        next++;
    }

    public void end() {
        next = middlewares.size() + 1;
    }

    public void goTo(int order) {
        if (middlewares.size() < order - 1 || order <= 0) {
            throw new IllegalArgumentException("Не найден указанный слой");
        }

        next = order;
    }

    public MiddlewareContext createSubContext(List<Middleware> middlewares) {
        return new MiddlewareContext(handlerResolver, middlewares);
    }
}
