package com.botconstructor.model.configuration.impl;

import com.botconstructor.model.configuration.ProviderConfig;

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
}
