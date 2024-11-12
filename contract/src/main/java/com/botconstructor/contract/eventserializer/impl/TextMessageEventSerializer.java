package com.botconstructor.contract.eventserializer.impl;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.eventserializer.EventSerializer;
import com.botconstructor.model.event.impl.TextMessageEvent;
import org.springframework.stereotype.Component;

@Component
public class TextMessageEventSerializer implements EventSerializer<TextMessageEvent> {
    @Override
    public void serialize(TextMessageEvent event, MiddlewareContext context) {
        var contextData = context.getContextData().withField("message");

        contextData.insert("chatId", event.getChatId());
        contextData.insert("text", event.getMessage());
    }
}
