package com.botconstructor.hosting.handler.impl.action;

import com.botconstructor.hosting.context.MiddlewareContext;
import com.botconstructor.hosting.handler.MiddlewareHandler;
import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.model.middleware.impl.action.impl.SendMessageAction;
import org.springframework.stereotype.Component;

@Component
public class SendMessageActionHandler implements MiddlewareHandler<SendMessageAction> {
    @Override
    public void act(SendMessageAction middleware, MiddlewareContext context, Provider<?> provider) {
        provider.SendTextMessage(middleware.getChatId(), middleware.getMessage());
    }
}
