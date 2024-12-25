package com.botconstructor.hosting.eventserializer.impl;

import com.botconstructor.hosting.context.MiddlewareContext;
import com.botconstructor.hosting.eventserializer.EventSerializer;
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

        var contextData = context.getContextData().withObject("command");

        contextData.insert("name", event.getCommandName());

        contextData.insert("params", event.getParamsString());
    }
}
