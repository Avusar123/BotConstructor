package com.botconstructor.contract.provider;

import com.botconstructor.contract.eventprocessor.EventProcessor;
import com.botconstructor.model.BotModel;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Provider<BotType extends BotModel> {
    protected BotType botModel;

    @Autowired
    protected EventProcessor eventProcessor;

    public Provider(BotType botModel) {
        this.botModel = botModel;
    }
}
