package com.botconstructor.controller;

import com.botconstructor.dto.data.block.ProcessingBlockDto;
import com.botconstructor.service.block.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BlockController {
    @Autowired
    private BlockService blockService;

    @PostMapping("/api/block")
    public ProcessingBlockDto createBlock(@RequestBody ProcessingBlockDto processingBlockDto,
                            @RequestParam UUID botId) {
        return blockService.create(
                processingBlockDto.getName(),
                processingBlockDto.getEventType(), botId);
    }

    @GetMapping("/api/block/all")
    public List<ProcessingBlockDto> getAllBlocks(@RequestParam UUID botId) {
        return blockService.getAll(botId);
    }
}
