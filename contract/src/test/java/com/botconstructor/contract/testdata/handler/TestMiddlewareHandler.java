package com.botconstructor.contract.testdata.handler;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.provider.Provider;
import com.botconstructor.contract.testdata.middleware.TestMiddleware;
import org.springframework.beans.factory.annotation.Autowired;

public class TestMiddlewareHandler implements MiddlewareHandler<TestMiddleware> {
    @Autowired
    private TestService testService;

    @Override
    public void act(TestMiddleware middleware, MiddlewareContext context, Provider<?> provider) {
        testService.use(middleware.getTestValue());
    }
}
