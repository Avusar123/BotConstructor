package com.botconstructor.contract.provider.impl;

import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.configuration.impl.TelegramProviderConfig;
import org.springframework.stereotype.Component;

@Component
public class TelegramProvider extends Provider<TelegramProviderConfig> {
    @Override
    public void StartListener() {
    }
}
