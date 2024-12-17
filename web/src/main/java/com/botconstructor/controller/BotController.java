package com.botconstructor.controller;

import com.botconstructor.dto.data.BotModelDto;
import com.botconstructor.dto.data.config.ProviderConfigDto;
import com.botconstructor.entrypoint.BotRunningEntrypoint;
import com.botconstructor.service.bot.BotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
public class BotController {

    @Autowired
    private BotService botService;

    @PostMapping("/api/bot")
    public BotModelDto createBot(@RequestParam String name) {
        return botService.create(name);
    }

    @PostMapping("/api/bot/config")
    public void setConfig(@RequestParam UUID id, @Valid @RequestBody ProviderConfigDto config) {
        botService.setConfig(id, config);
    }

    @GetMapping("/api/bot/config")
    public ProviderConfigDto getConfig(@RequestParam UUID id) {
        return botService.getConfig(id);
    }

    @GetMapping("/api/bot")
    public BotModelDto getBot(@RequestParam UUID id) {
        return botService.get(id);
    }

    @GetMapping("/api/bot/all")
    public List<BotModelDto> getAll() {
        return botService.getAll();
    }
}
