package com.botconstructor.contract.handler.impl.action;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.middleware.impl.action.impl.LogAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogActionHandler implements MiddlewareHandler<LogAction> {
    private static final Logger log = LoggerFactory.getLogger(LogActionHandler.class);

    @Override
    public void act(LogAction middleware, MiddlewareContext context, Provider<?> provider) {
        log.info(middleware.getMessage());
    }
}
