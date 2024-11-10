package com.botconstructor.contract.resolver;

import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.model.middleware.Middleware;

public interface HandlerResolver {
    MiddlewareHandler<Middleware> resolveHandler(Middleware middleware);
}
