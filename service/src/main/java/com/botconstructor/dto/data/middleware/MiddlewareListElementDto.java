package com.botconstructor.dto.data.middleware;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class MiddlewareListElementDto {
    private UUID id;

    private int order;

    private String name;

    private String type;

    private String subtype;

    public MiddlewareListElementDto(int order,
                                    UUID id,
                                    String name,
                                    String type,
                                    String subtype) {
        this.order = order;
        this.id = id;
        this.name = name;
        this.type = type;
        this.subtype = subtype;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
}
