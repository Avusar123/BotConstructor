package com.botconstructor.controller;

import com.botconstructor.dto.data.block.ProcessingBlockDto;
import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.dto.data.middleware.MiddlewareListElementDto;
import com.botconstructor.service.middleware.MiddlewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MiddlewareController {
    @Autowired
    MiddlewareService middlewareService;

    @PostMapping("/api/middleware")
    public MiddlewareDto createBlock(@RequestBody MiddlewareDto middlewareDto,
                                     @RequestParam int blockId,
                                     @RequestParam UUID botId) {
        return middlewareService.create(middlewareDto, blockId, botId);
    }

    @GetMapping("/api/middleware/all")
    public List<MiddlewareListElementDto> getAll(@RequestParam int blockId,
                                                 @RequestParam UUID botId) {
        return middlewareService.getAll(blockId, botId);
    }
}
