package com.botconstructor.service.block.impl;

import com.botconstructor.dto.converter.general.ConverterProvider;
import com.botconstructor.dto.data.block.ProcessingBlockDto;
import com.botconstructor.model.event.EventType;
import com.botconstructor.model.processingblock.ProcessingBlock;
import com.botconstructor.persistence.repos.BotRepo;
import com.botconstructor.persistence.repos.ProcessingBlockRepo;
import com.botconstructor.service.block.BlockService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@Validated
public class DefaultBlockService implements BlockService {
    @Autowired
    private ConverterProvider converterProvider;

    @Autowired
    private ProcessingBlockRepo processingBlockRepo;

    @Autowired
    private BotRepo botRepo;

    @Override
    @Transactional
    public ProcessingBlockDto create(
            @NotBlank String name,
            @NotNull EventType eventType,
            UUID botId) {
        var block = new ProcessingBlock(List.of(), eventType, name);

        processingBlockRepo.save(block);

        var bot = botRepo.findById(botId).orElseThrow();

        bot.addProcessingBlock(block);

        botRepo.save(bot);

        return converterProvider.getConverter(block, ProcessingBlockDto.class).toDto(block);
    }

    @Override
    public List<ProcessingBlockDto> getAll(UUID botId) {
        var blocks = processingBlockRepo.findBlocks(botId);

        return blocks.stream()
                .map(block -> converterProvider
                        .getConverter(block, ProcessingBlockDto.class).toDto(block))
                .toList();
    }
}