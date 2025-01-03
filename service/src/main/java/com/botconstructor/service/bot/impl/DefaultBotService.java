package com.botconstructor.service.bot.impl;

import com.botconstructor.dto.converter.general.ConverterProvider;
import com.botconstructor.dto.data.BotModelDto;
import com.botconstructor.dto.data.config.ProviderConfigDto;
import com.botconstructor.model.BotModel;
import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.model.user.UserModel;
import com.botconstructor.model.utils.RunningStatus;
import com.botconstructor.persistence.repos.BotRepo;
import com.botconstructor.service.bot.BotService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Component
@Validated
public class DefaultBotService implements BotService {
    @Autowired
    private BotRepo botRepo;

    @Autowired
    private ConverterProvider converterProvider;

    @Override
    public BotModelDto create(@NotBlank String name) {
        var bot = new BotModel(name, RunningStatus.NOT_INITIALIZED);

        bot = botRepo.save(bot);

        return converterProvider.getConverter(bot, BotModelDto.class).toDto(bot);
    }

    @Override
    @PreAuthorize("@ownershipChecker" +
            ".isOwner(#botId, T(com.botconstructor.model.BotModel))")
    public BotModelDto get(UUID botId) {
        var bot = botRepo.findById(botId).orElseThrow();

        return converterProvider.getConverter(bot, BotModelDto.class).toDto(bot);
    }

    @Override
    public List<BotModelDto> getAll() {
        var bots = botRepo.findAllByOwner(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName());

        if (bots.isEmpty()) {
            return List.of();
        }

        var converter = converterProvider.getConverter(bots.get(0), BotModelDto.class);

        return bots.stream().map(converter::toDto).toList();
    }

    @Override
    @Transactional
    @PreAuthorize("@ownershipChecker" +
            ".isOwner(#botId, T(com.botconstructor.model.BotModel))")
    public void setConfig(UUID botId, @Valid ProviderConfigDto config) {
        var bot = botRepo.findById(botId).orElseThrow();

        if (bot.getStatus() != RunningStatus.STOPPED && bot.getStatus() != RunningStatus.NOT_INITIALIZED) {
            throw new UnsupportedOperationException("Бот должен находится в состоянии ОСТАНОВЛЕН или НЕ ИНИЦИАЛИЗИРОВАН");
        }

        var conf = converterProvider
                .getConverter(ProviderConfig.class, config)
                .fromDto(config);

        bot.setProviderConfig(conf);

        bot.setStatus(RunningStatus.STOPPED);

        botRepo.save(bot);

    }

    @Override
    @PreAuthorize("@ownershipChecker" +
            ".isOwner(#botId, T(com.botconstructor.model.BotModel))")
    public ProviderConfigDto getConfig(UUID botId) {
        var bot = botRepo.findById(botId).orElseThrow();

        var config = bot.getProviderConfig();

        return converterProvider.getConverter(config, ProviderConfigDto.class).toDto(config);
    }
}
