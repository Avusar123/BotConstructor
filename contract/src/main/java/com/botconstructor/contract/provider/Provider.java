package com.botconstructor.contract.provider;

import com.botconstructor.model.BotModel;
import com.botconstructor.model.event.Event;
import com.botconstructor.model.processingblock.ProcessingBlock;

import java.util.List;

public abstract class Provider<BotType extends BotModel> {
    protected BotType botModel;

    public Provider(BotType botModel) {
        this.botModel = botModel;
    }

    protected List<ProcessingBlock> getProcessingBlocks(Event event) {
        return botModel.getProcessingBlocks()
                .stream()
                .filter(block -> block.getEventType().equals(event.getType()))
                .toList();
    }
}
