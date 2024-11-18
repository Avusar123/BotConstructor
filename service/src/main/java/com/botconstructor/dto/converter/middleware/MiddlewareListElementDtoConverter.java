package com.botconstructor.dto.converter.middleware;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.converter.general.ConverterProvider;
import com.botconstructor.dto.converter.middleware.util.MiddlewareDtoClassProvider;
import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.dto.data.middleware.MiddlewareListElementDto;
import com.botconstructor.model.middleware.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiddlewareListElementDtoConverter implements DtoConverter<Middleware, MiddlewareListElementDto> {
    @Autowired
    private ConverterProvider converterProvider;

    @Override
    public MiddlewareListElementDto toDto(Middleware middleware) {
        var converted = converterProvider.getConverter(middleware, MiddlewareDto.class).toDto(middleware);

        return new MiddlewareListElementDto(
                converted.getOrder(),
                converted.getId(),
                converted.getName(),
                converted.getType(),
                converted.getSubType()
        );
    }

    @Override
    public Middleware fromDto(MiddlewareListElementDto middlewareListElementDto) {
        throw new UnsupportedOperationException("Текущий объект не предполагает преобразование в объект БД!");
    }
}
