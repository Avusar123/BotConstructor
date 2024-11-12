package com.botconstructor.contract.provider;

import com.botconstructor.contract.eventprocessor.EventProcessor;
import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.model.processingblock.ProcessingBlock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class Provider<ConfigType extends ProviderConfig> {
    protected ConfigType config;

    @Autowired
    protected EventProcessor eventProcessor;

    private List<ProcessingBlock> processingBlocks;

    public void Initialize(ConfigType config, List<ProcessingBlock> blocks) {
        this.config = config;
        this.processingBlocks = blocks;
    }

    public abstract void StartListener();

    public abstract void SendTextMessage(String chatId, String text);

    protected List<ProcessingBlock> getProcessingBlocks() {
        return processingBlocks.stream().map(ProcessingBlock::clone).toList();
    }
}
