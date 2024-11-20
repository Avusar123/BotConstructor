package com.botconstructor.dto.converter.general.impl;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.converter.general.ConverterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
@Component
public class SpringConxtextGeneralConverterProvider implements ConverterProvider {
    @Autowired
    private ApplicationContext context;

    @Override
    public <MType, DtoType> DtoConverter<MType, DtoType>
    getConverter(MType model, Class<DtoType> dtoType) {
        return (DtoConverter<MType, DtoType>) getBean(model.getClass(), dtoType);
    }

    @Override
    public <MType, DtoType> DtoConverter<MType, DtoType> getConverter(Class<MType> model, DtoType dtoType) {
        return (DtoConverter<MType, DtoType>) getBean(model, dtoType.getClass());
    }

    private DtoConverter<?, ?> getBean(Class<?> model, Class<?> dtoType) {
        var beans = context.getBeansOfType(DtoConverter.class);

        for (var bean : beans.values()) {
            Type[] genericInterfaces = bean.getClass().getGenericInterfaces();

            List<Type> genericList = new ArrayList<>(Arrays.stream(genericInterfaces).toList());

            var parametrizedList = genericList
                    .stream()
                    .filter(t -> t instanceof ParameterizedType)
                    .map(t -> (ParameterizedType) t)
                    .toList();

            for (var type : parametrizedList) {
                var args = type.getActualTypeArguments();

                if (model.isAssignableFrom((Class<?>) args[0]) && dtoType.isAssignableFrom((Class<?>) args[1])) {
                    return bean;
                }
            }
        }

        throw new IllegalArgumentException("Указанный бин не найден!");
    }
}
