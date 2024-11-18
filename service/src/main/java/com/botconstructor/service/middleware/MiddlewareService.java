package com.botconstructor.service.middleware;

import com.botconstructor.dto.data.block.ProcessingBlockDto;
import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.dto.data.middleware.MiddlewareListElementDto;
import com.botconstructor.model.event.EventType;

import java.util.List;
import java.util.UUID;

public interface MiddlewareService {
    MiddlewareDto create(MiddlewareDto dto, int blockId, UUID botId);

    List<MiddlewareListElementDto> getAll(int blockId, UUID botId);
}
