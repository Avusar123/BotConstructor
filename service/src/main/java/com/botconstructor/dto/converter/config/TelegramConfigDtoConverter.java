package com.botconstructor.dto.converter.config;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.data.config.impl.TelegramConfigDto;
import com.botconstructor.model.configuration.impl.TelegramProviderConfig;
import org.springframework.stereotype.Component;

@Component
public class TelegramConfigDtoConverter
        implements DtoConverter<TelegramProviderConfig, TelegramConfigDto> {
    @Override
    public TelegramConfigDto toDto(TelegramProviderConfig telegramProviderConfig) {
        return new TelegramConfigDto(telegramProviderConfig.getToken());
    }

    @Override
    public TelegramProviderConfig fromDto(TelegramConfigDto telegramConfigDto) {
        return new TelegramProviderConfig(telegramConfigDto.getToken());
    }
}
