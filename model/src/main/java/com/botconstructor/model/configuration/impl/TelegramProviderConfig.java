package com.botconstructor.model.configuration.impl;

import com.botconstructor.model.configuration.ProviderConfig;
import jakarta.persistence.Entity;

@Entity
public class TelegramProviderConfig extends ProviderConfig {
    private String token;

    public TelegramProviderConfig(String token) {
        this.token = token;
    }

    protected TelegramProviderConfig() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
