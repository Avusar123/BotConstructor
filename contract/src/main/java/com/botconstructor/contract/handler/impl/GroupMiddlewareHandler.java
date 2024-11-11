package com.botconstructor.contract.handler.impl;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.middleware.impl.GroupMiddleware;
import org.springframework.stereotype.Component;

@Component
public class GroupMiddlewareHandler implements MiddlewareHandler<GroupMiddleware> {
    @Override
    public void act(GroupMiddleware middleware, MiddlewareContext context, Provider<?> provider) {
        var subContext = context.createSubContext(middleware.getMiddlewares());

        while (subContext.hasNext()) {
            subContext.next();
        }
    }
}
