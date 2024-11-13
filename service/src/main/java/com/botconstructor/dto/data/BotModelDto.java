package com.botconstructor.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class BotModelDto {
    @JsonProperty("id")
    private UUID botId;

    @JsonProperty("name")
    private String name;

    public BotModelDto(UUID botId, String name) {
        this.botId = botId;
        this.name = name;
    }

    public UUID getBotId() {
        return botId;
    }

    public void setBotId(UUID botId) {
        this.botId = botId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
