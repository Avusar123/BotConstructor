package com.botconstructor.entrypoint.impl;

import com.botconstructor.entrypoint.BotRunningEntrypoint;
import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.hosting.resolver.GenericResolver;
import com.botconstructor.model.BotModel;
import com.botconstructor.model.configuration.ProviderConfig;
import com.botconstructor.model.utils.RunningStatus;
import com.botconstructor.persistence.repos.BotRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LocalRunningEntrypoint implements BotRunningEntrypoint {
    private final Map<UUID, Provider<?>> activeProviders = new HashMap<>();
    @Autowired
    private BotRepo botRepo;
    @Autowired
    private GenericResolver resolver;

    @Override
    public void run(UUID botId) {
        var bot = botRepo.findById(botId).orElseThrow();

        markStarting(bot);

        var provider = resolver.resolveProvider(bot.getProviderConfig());

        initStart(bot, provider);

        activeProviders.put(bot.getId(), provider);
    }

    @Override
    public void stop(UUID botId) {
        var bot = botRepo.findById(botId).orElseThrow();

        markStopping(bot);

        initStop(bot);
    }

    @Transactional
    private void initStop(BotModel bot) {
        var provider = activeProviders.get(bot.getId());

        provider.StopListener();

        bot.setStatus(RunningStatus.STOPPED);

        botRepo.save(bot);
    }

    @Transactional
    private void initStart(BotModel bot, Provider<ProviderConfig> provider) {
        var loadedBot = loadAllRelations(bot);

        provider.Initialize(loadedBot.getProviderConfig(), loadedBot.getProcessingBlocks());

        provider.StartListener();

        bot.setStatus(RunningStatus.RUNNING);

        botRepo.save(bot);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    private void markStarting(BotModel bot) {
        if (bot.getStatus() != RunningStatus.STOPPED) {
            throw new UnsupportedOperationException("Бот не находится в статусе ВЫКЛЮЧЕН!");
        }

        bot.setStatus(RunningStatus.STARTING);

        botRepo.save(bot);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    private void markStopping(BotModel bot) {
        if (bot.getStatus() != RunningStatus.RUNNING) {
            throw new UnsupportedOperationException("Бот не находится в статусе ЗАПУЩЕН!");
        }

        bot.setStatus(RunningStatus.STOPPING);

        botRepo.save(bot);
    }

    private <T> T loadAllRelations(T entity) {
        if (entity == null) {
            return null;
        }

        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Collection.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                try {
                    Collection<?> collection = (Collection<?>) field.get(entity);
                    if (collection != null) {
                        for (Object item : collection) {
                            Hibernate.initialize(item);
                            loadAllRelations(item);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                field.setAccessible(false);
            }
        }
        return entity;
    }
}
