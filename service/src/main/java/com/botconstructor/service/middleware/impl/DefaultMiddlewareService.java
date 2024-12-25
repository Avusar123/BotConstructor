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
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
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
    @PreAuthorize("@ownershipChecker" +
            ".isOwner(#blockId, T(com.botconstructor.model.processingblock.ProcessingBlock))")
    public List<MiddlewareDto> createMany(@Valid List<MiddlewareDto> dtoList, UUID blockId) {
        var block = blockRepo.findById(blockId).orElseThrow();

        dtoList = dtoList.stream().sorted(Comparator.comparingInt(MiddlewareDto::getOrder)).toList();

        List<MiddlewareDto> result = new ArrayList<>();

        for (var dto : dtoList) {
            var converter = converterProvider.getConverter(Middleware.class, dto);

            var middleware = converter.fromDto(dto);

            middleware = saveMiddleware(middleware);

            block.addMiddleware(middleware);

            result.add(converter.toDto(middleware));

            blockRepo.save(block);
        }

        var validResult = Validations
                .isValid(block.getMiddlewares()
                        .stream()
                        .toList());

        if (!validResult.result()) {
            throw new IllegalArgumentException(validResult.message());
        }

        return result;
    }

    @Override
    @PreAuthorize("@ownershipChecker" +
            ".isOwner(#id, T(com.botconstructor.model.middleware.Middleware))")
    public MiddlewareDto get(UUID id) {
        var result = middlewareRepo.findById(id).orElseThrow();

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
    @PreAuthorize("@ownershipChecker" +
            ".isOwner(#blockId, T(com.botconstructor.model.processingblock.ProcessingBlock))")
    public List<MiddlewareListElementDto> getAll(UUID blockId) {
        var middlewares = middlewareRepo.findAllInBlock(blockId);

        return middlewares.stream()
                .map(mid -> listElementDtoConverter.toDto(mid))
                .toList();
    }
}
