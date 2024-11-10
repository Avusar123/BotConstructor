package com.botconstructor.contract.context;

import com.botconstructor.contract.resolver.GenericResolver;
import com.botconstructor.model.middleware.Middleware;

import java.util.List;

public class MiddlewareContext {
    private final GenericResolver genericResolver;
    private final List<Middleware> middlewares;
    private final MiddlewareContextData middlewareContextData;
    private int next;

    MiddlewareContext(GenericResolver genericResolver, List<Middleware> middlewares, MiddlewareContextData middlewareContextData) {
        this.genericResolver = genericResolver;
        this.middlewares = middlewares;
        this.middlewareContextData = middlewareContextData;
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
        genericResolver.resolveHandler(middleware).act(middleware, this);
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
        return new MiddlewareContext(genericResolver, middlewares, middlewareContextData);
    }

    public MiddlewareContextData getContextData() {
        return middlewareContextData;
    }
}
