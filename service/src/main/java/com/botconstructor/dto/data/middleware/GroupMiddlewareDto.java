package com.botconstructor.dto.data.middleware;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class GroupMiddlewareDto extends MiddlewareDto {
    @JsonProperty("middlewares")
    private List<MiddlewareDto> middlewares;

    public GroupMiddlewareDto(int order, UUID id, String name, List<MiddlewareDto> middlewares) {
        super(order, id, name);
        this.middlewares = middlewares;
    }

    @JsonCreator
    protected GroupMiddlewareDto(@JsonProperty(value = "middlewares", required = true) List<MiddlewareDto> middlewares,
                                 @JsonProperty(value = "order", required = true) int order,
                                 @JsonProperty(value = "name", required = true) String name) {
        super(order, name);
        this.middlewares = middlewares;
    }

    @Override
    public String getType() {
        return "general";
    }

    @Override
    public String getSubType() {
        return "group";
    }

    public List<MiddlewareDto> getMiddlewares() {
        return middlewares;
    }

    public void setMiddlewares(List<MiddlewareDto> middlewares) {
        this.middlewares = middlewares;
    }
}
