package com.botconstructor.hosting.provider.impl.telegram;

import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.model.configuration.impl.TelegramProviderConfig;
import com.botconstructor.model.event.Event;
import com.botconstructor.model.event.impl.CommandMessageEvent;
import com.botconstructor.model.event.impl.TextMessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

@SuppressWarnings("CallToPrintStackTrace")
@Component
public class TelegramProvider
        extends Provider<TelegramProviderConfig>
        implements LongPollingUpdateConsumer {

    @Autowired
    private TelegramBotsLongPollingApplication telegramApplication;

    private TelegramClient telegramClient;

    @Override
    public void StartListener() {
        try {
            telegramApplication.registerBot(config.getToken(), this);

            telegramClient = new OkHttpTelegramClient(config.getToken());

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void StopListener() {
        try {
            telegramApplication.unregisterBot(config.getToken());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void SendTextMessage(String chatId, String text) {
        try {
            telegramClient.execute(new SendMessage(chatId, text));
        } catch (TelegramApiException e) {
            throw new RuntimeException("Ошибка выполнения Telegram запроса!");
        }
    }

    @Override
    public void consume(List<Update> list) {
        try {
            Event event = null;

            for (var update : list) {
                if (update.hasMessage()) {
                    if (update.getMessage().hasText()) {
                        var message = update.getMessage();

                        var messageText = message.getText();

                        if (messageText.startsWith("/")) {

                            var commandName = messageText
                                    .split(" ", 2)[0]
                                    .substring(1);
                            String paramsString = "";

                            if (messageText.split(" ", 2).length > 1) {
                                paramsString = messageText
                                        .split(" ", 2)[1];
                            }


                            event = new CommandMessageEvent(
                                    message.getChat().getId().toString(),
                                    messageText,
                                    commandName,
                                    paramsString);
                        } else {
                            event = new TextMessageEvent(
                                    update.getMessage().getChat().getId().toString(),
                                    update.getMessage().getText());
                        }
                    }
                }

                if (event != null) {
                    eventProcessor.process(event, getProcessingBlocks(), this);
                }
            }
        } catch (Exception e) {
            System.err.println("ОШИБКА во время обработки сообщения: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
