package com.botconstructor.contract.handler.impl.trigger;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.middleware.impl.trigger.impl.TextMessageTrigger;
import kotlin.text.Regex;
import org.springframework.stereotype.Component;

@Component
public class TextMessageTriggerHandler implements MiddlewareHandler<TextMessageTrigger> {
    @Override
    public void act(TextMessageTrigger middleware, MiddlewareContext context, Provider<?> provider) {
        Regex pattern = new Regex(middleware.getRegex());

        var result = pattern.matches(context.getContextData().getValue("message:text"));

        if (!result) {
            context.end();
        }
    }
}
