package com.botconstructor.contract.handler.impl.trigger;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.eventserializer.impl.TextMessageEventSerializer;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.provider.Provider;
import com.botconstructor.contract.utils.Middlewares;
import com.botconstructor.model.middleware.impl.trigger.impl.CommandMessageTrigger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandMessageTriggerHandler implements MiddlewareHandler<CommandMessageTrigger> {
    @Override
    public void act(CommandMessageTrigger middleware, MiddlewareContext context, Provider<?> provider) {
        var contextData = context.getContextData().withField(middleware.getVariableToSave());

        if (!contextData.getValue("command:name").equals(middleware.getCommandName())) {
            context.end();
        }

        var paramsList = new ArrayList<>(
                List.of(
                        contextData.getValue("command:params")
                                .split(" ", middleware.getMaxParamsCount())
                ));

        Middlewares.serializeListToContextData(paramsList, contextData);
    }
}
