package com.botconstructor.hosting.eventserializer.impl;

import com.botconstructor.hosting.context.MiddlewareContext;
import com.botconstructor.hosting.eventserializer.EventSerializer;
import com.botconstructor.model.event.impl.TextMessageEvent;
import org.springframework.stereotype.Component;

@Component
public class TextMessageEventSerializer implements EventSerializer<TextMessageEvent> {
    @Override
    public void serialize(TextMessageEvent event, MiddlewareContext context) {
        var contextData = context.getContextData().withObject("message");

        contextData.insert("chatId", event.getChatId());
        contextData.insert("text", event.getMessage());
    }
}
