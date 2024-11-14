package com.botconstructor.hosting.handler;

import com.botconstructor.hosting.context.MiddlewareContext;
import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.model.middleware.Middleware;

public interface MiddlewareHandler<MType extends Middleware> {
    void act(MType middleware, MiddlewareContext context, Provider<?> provider);
}
