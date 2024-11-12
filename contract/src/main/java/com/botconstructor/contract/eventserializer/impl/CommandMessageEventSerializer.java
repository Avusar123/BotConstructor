package com.botconstructor.contract.eventserializer.impl;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.eventserializer.EventSerializer;
import com.botconstructor.contract.utils.Middlewares;
import com.botconstructor.model.event.impl.CommandMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandMessageEventSerializer implements EventSerializer<CommandMessageEvent> {
    @Autowired
    private TextMessageEventSerializer textMessageEventSerializer;

    @Override
    public void serialize(CommandMessageEvent event, MiddlewareContext context) {
        textMessageEventSerializer.serialize(event, context);

        var contextData = context.getContextData().withField("command");

        contextData.insert("name", event.getCommandName());

        contextData.insert("params", event.getParamsString());
    }
}
