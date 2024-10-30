package com.botconstructor.model;

import com.botconstructor.model.provider.configuration.ProviderConfig;
import com.botconstructor.model.trigger.Trigger;

import java.util.List;

/**
 * Модель бота для сохранения в БД.
 */
public class BotModel {
    List<Trigger> triggers;
    ProviderConfig providerConfig;
    String name;

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
}
