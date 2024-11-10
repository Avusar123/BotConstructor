package com.botconstructor.contract.resolver.impl;

import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.resolver.HandlerResolver;
import com.botconstructor.model.middleware.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class MiddlewareHandlerResolver implements HandlerResolver {
    @Autowired
    public ApplicationContext context;

    @Override
    public MiddlewareHandler<Middleware> resolveHandler(Middleware middleware) throws IllegalArgumentException {
        var beans = context.getBeansOfType(MiddlewareHandler.class);

        for (var bean : beans.values()) {
            Type[] genericInterfaces = bean.getClass().getGenericInterfaces();

            var parametrizedList = Arrays.stream(genericInterfaces).map(t -> (ParameterizedType)t).toList();

            for (var type : parametrizedList) {
                var args = type.getActualTypeArguments();

                if (args[0] == middleware.getClass()) {
                    return bean;
                }
            }
        }

        throw new IllegalArgumentException("Обработчик не найден!");
    }
}
