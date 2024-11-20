package com.botconstructor.service.bot;


import com.botconstructor.dto.data.BotModelDto;
import com.botconstructor.dto.data.config.ProviderConfigDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public interface BotService {
    BotModelDto create(@NotBlank String name);

    BotModelDto get(UUID botId);

    void setConfig(UUID botId, @Valid ProviderConfigDto config);

    ProviderConfigDto getConfig(UUID botId);
}
