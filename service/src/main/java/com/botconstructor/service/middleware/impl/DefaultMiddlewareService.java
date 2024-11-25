package com.botconstructor.service.middleware.impl;

import com.botconstructor.dto.converter.general.ConverterProvider;
import com.botconstructor.dto.converter.middleware.MiddlewareListElementDtoConverter;
import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.dto.data.middleware.MiddlewareListElementDto;
import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.middleware.impl.GroupMiddleware;
import com.botconstructor.model.validationutil.Validatable;
import com.botconstructor.model.validationutil.Validations;
import com.botconstructor.persistence.repos.MiddlewareRepo;
import com.botconstructor.persistence.repos.ProcessingBlockRepo;
import com.botconstructor.service.middleware.MiddlewareService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
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
    public List<MiddlewareDto> createMany(@Valid List<MiddlewareDto> dtoList, int blockId, UUID botId) {
        var block = blockRepo.findByIdInBot(botId, blockId).orElseThrow();

        dtoList = dtoList.stream().sorted(Comparator.comparingInt(MiddlewareDto::getOrder)).toList();

        List<MiddlewareDto> result = new ArrayList<>();

        for (var dto : dtoList) {
            var converter = converterProvider.getConverter(Middleware.class, dto);

            var middleware = converter.fromDto(dto);

            block.addMiddleware(middleware);

            result.add(converter.toDto(saveMiddleware(middleware)));

            blockRepo.save(block);
        }

        var validResult = Validations
                .isValid(block.getMiddlewares()
                        .stream()
                        .map(mid -> (Validatable) mid)
                        .toList());

        if (!validResult.result()) {
            throw new IllegalArgumentException(validResult.message());
        }

        return result;
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
