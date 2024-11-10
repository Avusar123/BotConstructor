package com.botconstructor.contract.provider.impl;

import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.configuration.ProviderConfigType;
import org.springframework.stereotype.Component;

@Component
public class TelegramProvider extends Provider {
    @Override
    public ProviderConfigType toConfigType() {
        return ProviderConfigType.TELEGRAM;
    }

    @Override
    public void StartListener() {
    }
}
