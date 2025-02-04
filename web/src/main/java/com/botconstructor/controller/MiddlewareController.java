package com.botconstructor.controller;

import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.dto.data.middleware.MiddlewareListElementDto;
import com.botconstructor.service.middleware.MiddlewareService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
public class MiddlewareController {
    @Autowired
    MiddlewareService middlewareService;

    @PostMapping("/api/middleware")
    public List<MiddlewareDto> createMiddleware(@Valid @RequestBody List<MiddlewareDto> middlewareDtoList,
                                     @RequestParam UUID blockId) {
        return middlewareService.createMany(middlewareDtoList, blockId);
    }

    @GetMapping("/api/middleware")
    public MiddlewareDto getMiddleware(@RequestParam UUID id) {
        return middlewareService.get(id);
    }

    @GetMapping("/api/middleware/all")
    public List<MiddlewareListElementDto> getAll(@RequestParam UUID blockId) {
        return middlewareService.getAll(blockId);
    }
}
