package com.botconstructor.model;

import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.model.processingblock.ProcessingBlock;
import com.botconstructor.model.user.OwnedEntity;
import com.botconstructor.model.user.UserModel;
import com.botconstructor.model.utils.RunningStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class BotModel extends OwnedEntity {
    @OneToMany(fetch = FetchType.LAZY)
    List<ProcessingBlock> processingBlocks;
    @OneToOne(cascade = CascadeType.ALL)
    ProviderConfig providerConfig;
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private RunningStatus status;

    public BotModel(String name, RunningStatus status) {
        this.name = name;
        this.status = status;
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

    public void setProcessingBlocks(List<ProcessingBlock> processingBlocks) {
        this.processingBlocks = processingBlocks;
    }

    public void addProcessingBlock(ProcessingBlock block) {
        processingBlocks.add(block);
    }

    public RunningStatus getStatus() {
        return status;
    }

    public void setStatus(RunningStatus status) {
        this.status = status;
    }
}
