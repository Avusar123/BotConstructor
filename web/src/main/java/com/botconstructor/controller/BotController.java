package com.botconstructor.controller;

import com.botconstructor.dto.data.BotModelDto;
import com.botconstructor.dto.data.config.ProviderConfigDto;
import com.botconstructor.service.bot.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class BotController {

    @Autowired
    private BotService botService;

    @PostMapping("/api/bot")
    public BotModelDto createBot(@RequestParam String name) {
        return botService.create(name);
    }

    @PostMapping("/api/bot/config")
    public void setConfig(@RequestParam UUID id, @RequestBody ProviderConfigDto config) {
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
}
