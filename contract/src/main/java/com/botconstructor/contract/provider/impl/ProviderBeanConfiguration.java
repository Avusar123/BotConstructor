package com.botconstructor.contract.provider.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

@Configuration
public class ProviderBeanConfiguration {
    @Bean
    public TelegramBotsLongPollingApplication telegramBotsLongPollingApplication() {
        return new TelegramBotsLongPollingApplication();
    }
}
