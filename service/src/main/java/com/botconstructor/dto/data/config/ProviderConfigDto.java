package com.botconstructor.dto.data.config;

import com.botconstructor.dto.data.config.impl.TelegramConfigDto;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TelegramConfigDto.class, name = "Telegram")
})
public abstract class ProviderConfigDto {
}
