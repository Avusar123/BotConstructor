package com.botconstructor.contract.tests.context;

import com.botconstructor.contract.context.MiddlewareContextFactory;
import com.botconstructor.contract.resolver.impl.MiddlewareHandlerResolver;
import com.botconstructor.contract.testdata.handler.TestMiddlewareHandler;
import com.botconstructor.contract.testdata.handler.TestService;
import com.botconstructor.contract.testdata.middleware.TestMiddleware;
import com.botconstructor.model.middleware.Middleware;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MiddlewareContextTest {
    @Test
    public void MiddlewareContext_next() {
        var middlewares = new ArrayList<Middleware>(List.of(
                new TestMiddleware(5, 1),
                new TestMiddleware(10, 2),
                new TestMiddleware(15, 3),
                new TestMiddleware(20, 4)));

        var mockService = Mockito.spy(TestService.class);

        var context = new AnnotationConfigApplicationContext();

        context.register(
                MiddlewareContextFactory.class,
                TestMiddlewareHandler.class,
                MiddlewareHandlerResolver.class
        );

        context.getBeanFactory().registerSingleton("testService", mockService);

        context.refresh();

        var middlewareContextFactory = context.getBean(MiddlewareContextFactory.class);

        var middlewareContext = middlewareContextFactory.withMiddlewares(middlewares).build();

        while (middlewareContext.hasNext()) {
            middlewareContext.next();
        }

        Mockito.verify(mockService, Mockito.times(4)).use(Mockito.anyInt());
    }
}