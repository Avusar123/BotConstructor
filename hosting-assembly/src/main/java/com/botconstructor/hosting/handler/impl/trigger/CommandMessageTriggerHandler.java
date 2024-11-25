package com.botconstructor.hosting.handler.impl.trigger;

import com.botconstructor.hosting.context.MiddlewareContext;
import com.botconstructor.hosting.handler.MiddlewareHandler;
import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.hosting.utils.Contexts;
import com.botconstructor.model.middleware.impl.trigger.impl.CommandMessageTrigger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandMessageTriggerHandler implements MiddlewareHandler<CommandMessageTrigger> {
    @Override
    public void act(CommandMessageTrigger middleware, MiddlewareContext context, Provider<?> provider) {
        var contextData = context.getContextData().withField(middleware.getVariableToSave());

        if (!contextData.getValue("command:name").equals(middleware.getCommandName())) {
            context.end();
        }

        List<String> paramsList;

        if (middleware.getMaxParamsCount() > 0) {
            paramsList = new ArrayList<>(
                    List.of(
                            contextData.getValue("command:params")
                                    .split(" ", middleware.getMaxParamsCount())
            ));

            Contexts.serializeListToContextData(paramsList, contextData);
        }
    }
}
