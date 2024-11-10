package com.botconstructor.controller;

import com.botconstructor.model.BotModel;
import com.botconstructor.repository.BotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
public class BotController {
    @Autowired
    private BotRepo botRepo;

    @PostMapping("/api/bot")
    public BotModel createBot(@RequestParam String name)
    {
        var bot = new BotModel(name);
        var result = botRepo.save(bot);;
        return result;
    }

    @GetMapping("/api/bot")
    public BotModel getBot(@RequestParam UUID id) {
        return botRepo.getReferenceById(id);
    }
}
