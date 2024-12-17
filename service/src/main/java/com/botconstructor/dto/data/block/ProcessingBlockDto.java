package com.botconstructor.dto.data.block;

import com.botconstructor.model.event.EventType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ProcessingBlockDto {

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("eventType")
    @NotNull
    private EventType eventType;

    @JsonProperty("id")
    private UUID id;

    @JsonCreator
    public ProcessingBlockDto(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "eventType", required = true) EventType eventType) {
        this.name = name;
        this.eventType = eventType;
    }

    public ProcessingBlockDto(String name, EventType eventType, UUID id) {
        this.name = name;
        this.eventType = eventType;
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
