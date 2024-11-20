package com.botconstructor.service.block;

import com.botconstructor.dto.data.block.ProcessingBlockDto;
import com.botconstructor.model.event.EventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface BlockService {
    ProcessingBlockDto create(@NotBlank String name,
                              @NotNull EventType eventType,
                              UUID botId);

    List<ProcessingBlockDto> getAll(UUID botId);
}
