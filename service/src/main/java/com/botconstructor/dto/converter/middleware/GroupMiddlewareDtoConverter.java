package com.botconstructor.dto.converter.middleware;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.converter.general.ConverterProvider;
import com.botconstructor.dto.data.middleware.GroupMiddlewareDto;
import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.middleware.impl.GroupMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupMiddlewareDtoConverter implements DtoConverter<GroupMiddleware, GroupMiddlewareDto> {
    @Autowired
    private ConverterProvider provider;

    @Override
    public GroupMiddlewareDto toDto(GroupMiddleware groupMiddleware) {
        return new GroupMiddlewareDto(
                groupMiddleware.getOrderValue(),
                groupMiddleware.getId(),
                groupMiddleware.getName(),
                groupMiddleware
                        .getMiddlewares()
                        .stream()
                        .map(mid -> provider.getConverter(mid, MiddlewareDto.class)
                                .toDto(mid)).toList()
        );
    }

    @Override
    public GroupMiddleware fromDto(GroupMiddlewareDto groupMiddlewareDto) {
        return new GroupMiddleware(
                groupMiddlewareDto.getOrder(),
                groupMiddlewareDto.getName(),
                groupMiddlewareDto
                        .getMiddlewares()
                        .stream()
                        .map(mid -> provider.getConverter(Middleware.class, mid)
                                .fromDto(mid)).toList()
        );
    }
}
