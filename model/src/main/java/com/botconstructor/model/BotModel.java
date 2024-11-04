package com.botconstructor.model;

import com.botconstructor.model.data.configuration.ProviderConfig;
import com.botconstructor.model.data.trigger.Trigger;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

/**
 * Модель бота для сохранения в БД.
 */
@Entity
public class BotModel {

    @OneToMany
    List<Trigger> triggers;

    @OneToOne
    ProviderConfig providerConfig;
    private String name;

    @Id
    @GeneratedValue
    @JsonInclude
    private UUID id;

    public BotModel(String name) {
        this.name = name;
    }

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

    public void addTrigger(Trigger trigger) { this.triggers.add(trigger); }

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
}
