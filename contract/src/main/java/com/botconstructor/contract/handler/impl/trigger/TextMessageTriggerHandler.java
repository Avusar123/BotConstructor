package com.botconstructor.contract.handler.impl.trigger;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.middleware.impl.trigger.impl.TextMessageTrigger;
import org.springframework.stereotype.Component;

@Component
public class TextMessageTriggerHandler implements MiddlewareHandler<TextMessageTrigger> {
    @Override
    public void act(TextMessageTrigger middleware, MiddlewareContext context, Provider<?> provider) {
        if (!middleware.getRegex().equals(context.getContextData().getValue("message:text"))) {
            context.end();
        }
    }
}
