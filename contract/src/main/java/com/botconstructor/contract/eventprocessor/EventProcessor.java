package com.botconstructor.contract.eventprocessor;

import com.botconstructor.model.event.Event;
import com.botconstructor.model.processingblock.ProcessingBlock;

import java.util.List;

public interface EventProcessor {
    void process(Event event, List<ProcessingBlock> processingBlocks);
}
