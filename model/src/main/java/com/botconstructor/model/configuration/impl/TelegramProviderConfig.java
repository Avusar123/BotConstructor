package com.botconstructor.model.configuration.impl;

import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.model.configuration.ProviderConfigType;

public class TelegramProviderConfig extends ProviderConfig {
    private String token;

    public TelegramProviderConfig(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public ProviderConfigType toConfigType() {
        return ProviderConfigType.TELEGRAM;
    }
}
