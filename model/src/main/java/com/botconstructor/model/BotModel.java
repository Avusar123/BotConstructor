package com.botconstructor.model;

import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.model.processingblock.ProcessingBlock;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class BotModel {
    @OneToMany
    List<ProcessingBlock> processingBlocks;
    @OneToOne(cascade = CascadeType.ALL)
    ProviderConfig providerConfig;
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    public BotModel(String name) {
        this.name = name;
    }

    protected BotModel() {
        processingBlocks = new ArrayList<>();
    }

    public ProviderConfig getProviderConfig() {
        return providerConfig;
    }

    public void setProviderConfig(ProviderConfig providerConfig) {
        this.providerConfig = providerConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ProcessingBlock> getProcessingBlocks() {
        return processingBlocks;
    }

    public void addProcessingBlock(ProcessingBlock block) {
        processingBlocks.add(block);
    }

    public void setProcessingBlocks(List<ProcessingBlock> processingBlocks) {
        this.processingBlocks = processingBlocks;
    }
}
