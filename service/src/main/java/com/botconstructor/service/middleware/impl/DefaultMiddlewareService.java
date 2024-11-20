package com.botconstructor.service.middleware.impl;

import com.botconstructor.dto.converter.general.ConverterProvider;
import com.botconstructor.dto.converter.middleware.MiddlewareListElementDtoConverter;
import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.dto.data.middleware.MiddlewareListElementDto;
import com.botconstructor.hosting.utils.Middlewares;
import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.middleware.impl.GroupMiddleware;
import com.botconstructor.persistence.repos.MiddlewareRepo;
import com.botconstructor.persistence.repos.ProcessingBlockRepo;
import com.botconstructor.service.middleware.MiddlewareService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class DefaultMiddlewareService implements MiddlewareService {
    @Autowired
    private MiddlewareRepo middlewareRepo;

    @Autowired
    private ProcessingBlockRepo blockRepo;

    @Autowired
    private ConverterProvider converterProvider;

    @Autowired
    private MiddlewareListElementDtoConverter listElementDtoConverter;

    @Override
    @Transactional
    public MiddlewareDto create(@Valid MiddlewareDto dto, int blockId, UUID botId) {
        var block = blockRepo.findByIdInBot(botId, blockId).orElseThrow();

        var converter = converterProvider.getConverter(Middleware.class, dto);

        var middleware = converter.fromDto(dto);

        block.addMiddleware(middleware);

        if (!Middlewares.verifyOrders(block.getMiddlewares())) {
            throw new IllegalArgumentException("Порядок должен начинаться с 1 и не должен прерываться!");
        }

        var modifiedMid = saveMiddleware(middleware);

        blockRepo.save(block);

        return converter.toDto(modifiedMid);
    }

    @Override
    public MiddlewareDto get(int id, UUID botId) {
        var result = middlewareRepo.findInBotById(id, botId);

        return converterProvider.getConverter(result, MiddlewareDto.class).toDto(result);
    }

    @Transactional
    private Middleware saveMiddleware(Middleware middleware) {
        if (middleware instanceof GroupMiddleware) {
            for (var mid : ((GroupMiddleware) middleware).getMiddlewares()) {
                saveMiddleware(mid);
            }
        }

        return middlewareRepo.save(middleware);
    }

    @Override
    public List<MiddlewareListElementDto> getAll(int blockId, UUID botId) {
        var middlewares = middlewareRepo.findAllInBotByBlockId(botId, blockId);

        return middlewares.stream()
                .map(mid -> listElementDtoConverter.toDto(mid))
                .toList();
    }
}
