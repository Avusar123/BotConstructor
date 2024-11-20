package com.botconstructor.controller;

import com.botconstructor.entrypoint.BotRunningEntrypoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Controller
@RestController
public class RunningController {
    @Autowired
    private BotRunningEntrypoint runningEntrypoint;

    @PostMapping("/api/running")
    public void startBot(@RequestParam UUID botId) {
        runningEntrypoint.run(botId);
    }

    @DeleteMapping("/api/running")
    public void stopBot(@RequestParam UUID botId) {
        runningEntrypoint.stop(botId);
    }
}
