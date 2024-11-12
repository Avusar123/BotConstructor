package com.botconstructor.contract.eventprocessor.impl;

import com.botconstructor.contract.context.MiddlewareContext;
import com.botconstructor.contract.context.MiddlewareContextFactory;
import com.botconstructor.contract.eventprocessor.EventProcessor;
import com.botconstructor.contract.provider.Provider;
import com.botconstructor.contract.resolver.GenericResolver;
import com.botconstructor.model.event.Event;
import com.botconstructor.model.processingblock.ProcessingBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleEventProcessor implements EventProcessor {
    @Autowired
    private GenericResolver resolver;

    @Autowired
    private MiddlewareContextFactory middlewareContextFactory;

    @Override
    public void process(Event event, List<ProcessingBlock> processingBlocks, Provider<?> provider) {

        var filteredBlocks = filterBlocks(event, processingBlocks);

        var serializer = resolver.resolveSerializer(event);

        for (var block : filteredBlocks) {
            var middlewares = block.getMiddlewares();

            var context = middlewareContextFactory
                    .withMiddlewares(middlewares)
                    .withProvider(provider)
                    .build();

            serializer.serialize(event, context);

            processContext(context);
        }
    }

    private List<ProcessingBlock> filterBlocks(Event event, List<ProcessingBlock> processingBlocks) {
        return processingBlocks
                .stream()
                .filter(block -> block.getEventType().equals(event.getType()))
                .toList();
    }

    private void processContext(MiddlewareContext middlewareContext) {
        while (middlewareContext.hasNext()) {
            middlewareContext.next();
        }
    }
}
