package com.botconstructor.dto.data.block;

import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.model.event.EventType;
import com.botconstructor.model.processingblock.ProcessingBlock;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProcessingBlockDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("eventType")
    private EventType eventType;

    @JsonProperty("id")
    private int id;

    @JsonCreator
    public ProcessingBlockDto(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "eventType", required = true) EventType eventType) {
        this.name = name;
        this.eventType = eventType;
    }

    public ProcessingBlockDto(String name, EventType eventType, int id) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
