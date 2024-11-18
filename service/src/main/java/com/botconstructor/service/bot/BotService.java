package com.botconstructor.service.bot;


import com.botconstructor.dto.data.BotModelDto;
import com.botconstructor.dto.data.config.ProviderConfigDto;

import java.util.UUID;

public interface BotService {
    BotModelDto create(String name);

    BotModelDto get(UUID botId);

    void setConfig(UUID botId, ProviderConfigDto config);

    ProviderConfigDto getConfig(UUID botId);
}
