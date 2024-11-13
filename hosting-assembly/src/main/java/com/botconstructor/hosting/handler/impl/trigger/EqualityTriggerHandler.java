package com.botconstructor.hosting.handler.impl.trigger;

import com.botconstructor.hosting.context.MiddlewareContext;
import com.botconstructor.hosting.handler.MiddlewareHandler;
import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.model.middleware.impl.trigger.impl.EqualityTrigger;
import org.springframework.stereotype.Component;

@Component
public class EqualityTriggerHandler implements MiddlewareHandler<EqualityTrigger> {
    @Override
    public void act(EqualityTrigger middleware, MiddlewareContext context, Provider<?> provider) {
        var hasEquation = middleware.getFirstValue().equals(middleware.getLastValue());

        if ((middleware.isInverted() && hasEquation) || (!middleware.isInverted() && !hasEquation)) {
            context.end();
        }
    }
}
