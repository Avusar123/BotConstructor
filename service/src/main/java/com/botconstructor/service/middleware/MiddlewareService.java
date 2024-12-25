package com.botconstructor.service.middleware;

import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.dto.data.middleware.MiddlewareListElementDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface MiddlewareService {
    List<MiddlewareDto> createMany(@Valid List<MiddlewareDto> dtoList, UUID blockId);

    MiddlewareDto get(UUID id);

    List<MiddlewareListElementDto> getAll(UUID blockId);
}
