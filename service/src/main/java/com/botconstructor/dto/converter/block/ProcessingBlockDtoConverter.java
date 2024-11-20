package com.botconstructor.dto.converter.block;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.data.block.ProcessingBlockDto;
import com.botconstructor.model.processingblock.ProcessingBlock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProcessingBlockDtoConverter
        implements DtoConverter<ProcessingBlock, ProcessingBlockDto> {

    @Override
    public ProcessingBlockDto toDto(ProcessingBlock processingBlock) {
        return new ProcessingBlockDto(
                processingBlock.getName(),
                processingBlock.getEventType(),
                processingBlock.getId()
        );
    }

    @Override
    public ProcessingBlock fromDto(ProcessingBlockDto processingBlockDto) {
        return new ProcessingBlock(
                new ArrayList<>(),
                processingBlockDto.getEventType(),
                processingBlockDto.getName(),
                processingBlockDto.getId()
        );
    }
}
