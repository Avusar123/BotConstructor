package com.botconstructor.service.block;

import com.botconstructor.dto.data.block.ProcessingBlockDto;
import com.botconstructor.model.event.EventType;

import java.util.List;
import java.util.UUID;

public interface BlockService {
    ProcessingBlockDto create(String name, EventType eventType, UUID botId);

    List<ProcessingBlockDto> getAll(UUID botId);
}
