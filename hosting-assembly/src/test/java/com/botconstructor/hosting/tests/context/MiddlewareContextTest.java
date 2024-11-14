package com.botconstructor.hosting.tests.context;

import com.botconstructor.hosting.context.MiddlewareContextFactory;
import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.hosting.resolver.impl.ReflectionHandlerResolver;
import com.botconstructor.hosting.testdata.handler.TestMiddlewareHandler;
import com.botconstructor.hosting.testdata.handler.TestService;
import com.botconstructor.hosting.testdata.middleware.TestMiddleware;
import com.botconstructor.model.middleware.Middleware;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

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
                ReflectionHandlerResolver.class
        );

        context.getBeanFactory().registerSingleton("testService", mockService);

        context.refresh();

        var middlewareContextFactory = context.getBean(MiddlewareContextFactory.class);

        var middlewareContext = middlewareContextFactory
                .withMiddlewares(middlewares)
                .withProvider(Mockito.spy(Provider.class))
                .build();

        while (middlewareContext.hasNext()) {
            middlewareContext.next();
        }

        Mockito.verify(mockService, Mockito.times(4)).use(Mockito.anyInt());
    }
}