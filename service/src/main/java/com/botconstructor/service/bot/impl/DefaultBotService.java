package com.botconstructor.service.bot.impl;

import com.botconstructor.dto.converter.general.ConverterProvider;
import com.botconstructor.dto.data.BotModelDto;
import com.botconstructor.dto.data.config.ProviderConfigDto;
import com.botconstructor.model.BotModel;
import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.persistence.repos.BotRepo;
import com.botconstructor.service.bot.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultBotService implements BotService {
    @Autowired
    private BotRepo botRepo;

    @Autowired
    private ConverterProvider converterProvider;

    @Override
    public BotModelDto create(String name) {
        var bot = botRepo.save(new BotModel(name));

        return converterProvider.getConverter(bot, BotModelDto.class).toDto(bot);
    }

    @Override
    public BotModelDto get(UUID botId) {
        var bot = botRepo.findById(botId).orElseThrow();

        return converterProvider.getConverter(bot, BotModelDto.class).toDto(bot);
    }

    @Override
    public void setConfig(UUID botId, ProviderConfigDto config) {
        var bot = botRepo.findById(botId).orElseThrow();

        var conf = converterProvider
                .getConverter(ProviderConfig.class, config)
                .fromDto(config);

        bot.setProviderConfig(conf);

        botRepo.save(bot);

    }

    @Override
    public ProviderConfigDto getConfig(UUID botId) {
        var bot = botRepo.findById(botId).orElseThrow();

        var config = bot.getProviderConfig();

        return converterProvider.getConverter(config, ProviderConfigDto.class).toDto(config);
    }
}
