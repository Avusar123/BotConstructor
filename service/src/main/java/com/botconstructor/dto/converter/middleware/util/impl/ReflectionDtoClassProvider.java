package com.botconstructor.dto.converter.middleware.util.impl;

import com.botconstructor.dto.converter.middleware.util.MiddlewareDtoClassProvider;
import com.botconstructor.dto.data.middleware.MiddlewareDto;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;

@Component
public class ReflectionDtoClassProvider implements MiddlewareDtoClassProvider {

    private List<? extends MiddlewareDto> middlewares;

    @Override
    public Class<? extends MiddlewareDto> getClass(String type, String subtype) {
        if (middlewares == null) {
            scanClasses();
        }

        return middlewares.stream()
                .filter(mid ->
                        Objects.equals(mid.getType(), type)
                        && Objects.equals(mid.getSubType(), subtype))
                .map(mid -> mid.getClass())
                .findAny()
                .orElseThrow();
    }

    private void scanClasses() {
        var reflections = new Reflections("com.botconstructor.dto.data.middleware");

        Objenesis objenesis = new ObjenesisStd();

        middlewares = reflections.getSubTypesOf(MiddlewareDto.class)
                .stream()
                .filter(mid -> !Modifier.isAbstract(mid.getModifiers()))
                .map(objenesis::newInstance)
                .toList();
    }
}
