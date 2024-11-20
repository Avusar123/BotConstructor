package com.botconstructor.service.middleware;

import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.dto.data.middleware.MiddlewareListElementDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface MiddlewareService {
    MiddlewareDto create(@Valid MiddlewareDto dto, int blockId, UUID botId);

    MiddlewareDto get(int id, UUID botId);

    List<MiddlewareListElementDto> getAll(int blockId, UUID botId);
}
