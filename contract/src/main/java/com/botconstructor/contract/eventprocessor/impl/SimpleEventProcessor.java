package com.botconstructor.contract.eventprocessor.impl;

import com.botconstructor.contract.eventprocessor.EventProcessor;
import com.botconstructor.model.event.Event;
import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.processingblock.ProcessingBlock;

import java.util.List;

public class SimpleEventProcessor implements EventProcessor {
    @Override
    public void process(Event event, List<ProcessingBlock> processingBlocks) {
    }
}
