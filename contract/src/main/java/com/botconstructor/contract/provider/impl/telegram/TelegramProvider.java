package com.botconstructor.contract.provider.impl.telegram;

import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.configuration.impl.TelegramProviderConfig;
import com.botconstructor.model.event.Event;
import com.botconstructor.model.event.impl.TextMessageEvent;
import com.botconstructor.model.processingblock.ProcessingBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
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
            telegramApplication.registerBot(config.getToken(), this).getOkHttpClient();

            telegramClient = new OkHttpTelegramClient(config.getToken());

        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось зарегистрировать бота!");
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
                        event = new TextMessageEvent(
                                update.getMessage().getChat().getId().toString(),
                                update.getMessage().getText());
                    }
                }

                if (event != null) {
                    eventProcessor.process(event, getProcessingBlocks(), this);
                }
            }
        } catch (Exception e) {
            System.err.println("Error in task: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
