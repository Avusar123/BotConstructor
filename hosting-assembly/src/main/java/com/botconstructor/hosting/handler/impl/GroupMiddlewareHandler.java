package com.botconstructor.hosting.handler.impl;

import com.botconstructor.hosting.context.MiddlewareContext;
import com.botconstructor.hosting.handler.MiddlewareHandler;
import com.botconstructor.hosting.provider.Provider;
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
