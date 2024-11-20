package com.botconstructor.dto.data.middleware;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public abstract class MiddlewareDto {
    @JsonProperty("order")
    private int order;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    @NotBlank
    private String name;

    public MiddlewareDto(int order, int id, String name) {
        this.order = order;
        this.id = id;
        this.name = name;
    }

    public MiddlewareDto(int order, String name) {
        this.order = order;
        this.name = name;
    }

    @JsonProperty("type")
    public abstract String getType();

    @JsonProperty("subtype")
    public abstract String getSubType();

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
