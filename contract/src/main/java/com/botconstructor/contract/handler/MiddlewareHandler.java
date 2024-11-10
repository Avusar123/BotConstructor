package com.botconstructor.contract.handler;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.model.middleware.Middleware;

public interface MiddlewareHandler<MType extends Middleware> {
    void act(MType middleware, MiddlewareContext context);
}
