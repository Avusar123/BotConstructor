package com.botconstructor.contract.testdata.handler;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.testdata.middleware.TestMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class TestMiddlewareHandler implements MiddlewareHandler<TestMiddleware> {
    @Autowired
    private TestService testService;

    @Override
    public void act(TestMiddleware middleware, MiddlewareContext context) {
        testService.use(middleware.getTestValue());
    }
}
