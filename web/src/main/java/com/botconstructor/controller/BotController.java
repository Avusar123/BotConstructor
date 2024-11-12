package com.botconstructor.controller;

import com.botconstructor.contract.resolver.GenericResolver;
import com.botconstructor.model.configuration.impl.TelegramProviderConfig;
import com.botconstructor.model.event.EventType;
import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.middleware.impl.GroupMiddleware;
import com.botconstructor.model.middleware.impl.action.impl.LogAction;
import com.botconstructor.model.middleware.impl.action.impl.SendMessageAction;
import com.botconstructor.model.middleware.impl.trigger.impl.CommandMessageTrigger;
import com.botconstructor.model.middleware.impl.trigger.impl.EqualityTrigger;
import com.botconstructor.model.middleware.impl.trigger.impl.TextMessageTrigger;
import com.botconstructor.model.processingblock.ProcessingBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class BotController {

    @Autowired
    private GenericResolver genericResolver;

    @PostMapping("/api/bot")
    public void createBot(@RequestParam String token)
    {
        var configuration = new TelegramProviderConfig(token);

        var middlewares1 = new ArrayList<Middleware>();

        middlewares1.add(new EqualityTrigger(
                1,
                "{message:chatId}",
                "1157583330",
                false));

        middlewares1.add(new CommandMessageTrigger(
                2,
                "send",
                2,
                "sendData"
        ));
        middlewares1.add(new SendMessageAction(
                3,
                "{sendData:2}",
                "{sendData:1}"
        ));
        middlewares1.add(new LogAction(
                4,
                "Отправляю {sendData:2} на {sendData:1}"
        ));

        var processingBlock1 = new ProcessingBlock(middlewares1, EventType.COMMAND_MESSAGE);

        var middlewares2 = new ArrayList<Middleware>();

        middlewares2.add(new LogAction(
                1,
                "Логирую {message:text} от {message:chatId}"));
        middlewares2.add(new EqualityTrigger(
                2,
                "{message:chatId}",
                "1157583330",
                true));
        middlewares2.add(new SendMessageAction(
                3,
                "{message:chatId}: {message:text}",
                "1157583330"
        ));

        var processingBlock2 = new ProcessingBlock(middlewares2, EventType.TEXT_MESSAGE);

        var provider = genericResolver.resolveProvider(configuration);

        provider.Initialize(configuration, List.of(processingBlock1, processingBlock2));

        provider.StartListener();
    }
}
