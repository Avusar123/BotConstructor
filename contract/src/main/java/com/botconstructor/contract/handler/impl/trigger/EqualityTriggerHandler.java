package com.botconstructor.contract.handler.impl.trigger;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.provider.Provider;
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
