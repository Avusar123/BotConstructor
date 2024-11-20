package com.botconstructor.dto.data;

import com.botconstructor.model.utils.RunningStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class BotModelDto {
    @JsonProperty("id")
    @NotNull
    private UUID botId;

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("status")
    @NotNull
    private RunningStatus status;

    public BotModelDto(UUID botId, String name, RunningStatus status) {
        this.botId = botId;
        this.name = name;
        this.status = status;
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

    public RunningStatus getStatus() {
        return status;
    }

    public void setStatus(RunningStatus status) {
        this.status = status;
    }
}
