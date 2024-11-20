package com.botconstructor.entrypoint;

import java.util.UUID;

public interface BotRunningEntrypoint {
    void run(UUID botId);

    void stop(UUID botId);
}
