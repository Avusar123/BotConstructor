package com.botconstructor.hosting.testdata.handler;

import com.botconstructor.hosting.context.MiddlewareContext;
import com.botconstructor.hosting.handler.MiddlewareHandler;
import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.hosting.testdata.middleware.TestMiddleware;
import org.springframework.beans.factory.annotation.Autowired;

public class TestMiddlewareHandler implements MiddlewareHandler<TestMiddleware> {
    @Autowired
    private TestService testService;

    @Override
    public void act(TestMiddleware middleware, MiddlewareContext context, Provider<?> provider) {
        testService.use(middleware.getTestValue());
    }
}
