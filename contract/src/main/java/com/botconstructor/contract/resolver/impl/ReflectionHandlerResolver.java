package com.botconstructor.contract.resolver.impl;

import com.botconstructor.contract.eventserializer.EventSerializer;
import com.botconstructor.contract.handler.MiddlewareHandler;
import com.botconstructor.contract.resolver.GenericResolver;
import com.botconstructor.model.event.Event;
import com.botconstructor.model.middleware.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

@Component
public class ReflectionHandlerResolver implements GenericResolver {
    @Autowired
    public ApplicationContext context;

    @Override
    public MiddlewareHandler<Middleware> resolveHandler(Middleware middleware) throws IllegalArgumentException {
        return getBean(MiddlewareHandler.class, middleware.getClass());
    }

    @Override
    public EventSerializer<Event> resolveSerializer(Event event) {
        return getBean(EventSerializer.class, event.getClass());
    }

    private <T> T getBean(Class<T> superClass, Class<?> genericClass) {
        var beans = context.getBeansOfType(superClass);

        for (var bean : beans.values()) {
            Type[] genericInterfaces = bean.getClass().getGenericInterfaces();

            var parametrizedList = Arrays.stream(genericInterfaces).map(t -> (ParameterizedType) t).toList();

            for (var type : parametrizedList) {
                var args = type.getActualTypeArguments();

                if (args[0] == genericClass) {
                    return (T) bean;
                }
            }
        }

        throw new IllegalArgumentException("Указанный бин не найден!");
    }
}
