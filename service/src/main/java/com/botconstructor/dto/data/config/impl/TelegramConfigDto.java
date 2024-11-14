package com.botconstructor.dto.data.config.impl;

import com.botconstructor.dto.data.config.ProviderConfigDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TelegramConfigDto extends ProviderConfigDto {
    @JsonProperty("token")
    private String token;

    @JsonCreator
    public TelegramConfigDto(@JsonProperty("token") String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
