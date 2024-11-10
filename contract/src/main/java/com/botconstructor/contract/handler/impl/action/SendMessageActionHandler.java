package com.botconstructor.contract.handler.impl.action;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.middleware.impl.action.impl.SendMessageAction;
import org.springframework.stereotype.Component;

@Component
public class SendMessageActionHandler implements MiddlewareHandler<SendMessageAction> {
    @Override
    public void act(SendMessageAction middleware, MiddlewareContext context, Provider<?> provider) {
        provider.SendTextMessage(middleware.getChatId(), middleware.getMessage());
    }
}
