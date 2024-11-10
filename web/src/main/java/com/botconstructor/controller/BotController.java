package com.botconstructor.controller;

import com.botconstructor.contract.resolver.GenericResolver;
import com.botconstructor.model.configuration.impl.TelegramProviderConfig;
import com.botconstructor.model.event.EventType;
import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.middleware.impl.action.impl.LogAction;
import com.botconstructor.model.middleware.impl.action.impl.SendMessageAction;
import com.botconstructor.model.middleware.impl.trigger.impl.TextMessageTrigger;
import com.botconstructor.model.processingblock.ProcessingBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
public class BotController {

    @Autowired
    private GenericResolver genericResolver;

    @PostMapping("/api/bot")
    public void createBot(@RequestParam String token)
    {
        var configuration = new TelegramProviderConfig(token);

        var middlewares = new ArrayList<Middleware>();

        middlewares.add(new TextMessageTrigger(1, "Привет"));

        middlewares.add(new LogAction(2, "Логирую привет.."));

        middlewares.add(new SendMessageAction(3, "Привет, {message:chatId}", "{message:chatId}"));

        var processingBlock = new ProcessingBlock(middlewares, EventType.TEXT_MESSAGE);

        var provider = genericResolver.resolveProvider(configuration);

        provider.Initialize(configuration, List.of(processingBlock));

        provider.StartListener();
    }
}
