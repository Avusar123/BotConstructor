package com.botconstructor.model;

import com.botconstructor.model.processingblock.ProcessingBlock;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

import com.botconstructor.model.configuration.ProviderConfig;

@Entity
public class BotModel {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany
    List<ProcessingBlock> processingBlocks;

    @OneToOne(cascade = CascadeType.ALL)
    ProviderConfig providerConfig;

    private String name;

    public BotModel(String name) {
        this.name = name;
    }

    protected BotModel() {

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

    public void setProcessingBlocks(List<ProcessingBlock> processingBlocks) {
        this.processingBlocks = processingBlocks;
    }
}
