package com.botconstructor.contract.provider;

import com.botconstructor.contract.eventprocessor.EventProcessor;
import com.botconstructor.model.BotModel;
import com.botconstructor.model.configuration.ConfigEnumConverter;
import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.model.configuration.ProviderConfigType;
import com.botconstructor.model.processingblock.ProcessingBlock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class Provider implements ConfigEnumConverter {
    protected ProviderConfig config;

    @Autowired
    protected EventProcessor eventProcessor;

    protected List<ProcessingBlock> processingBlocks;

    public void Initialize(ProviderConfig config, List<ProcessingBlock> blocks) {
        if (config.toConfigType() != this.toConfigType()) {
            throw new IllegalArgumentException("Тип конфига не совпадает с нужным!");
        }

        this.config = config;
        this.processingBlocks = blocks;
    }

    public abstract void StartListener();
}
