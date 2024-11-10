package com.botconstructor.model.processingblock;

import com.botconstructor.model.event.EventType;
import com.botconstructor.model.middleware.Middleware;
import jakarta.persistence.*;

import java.lang.annotation.Inherited;
import java.util.List;

@Entity
public class ProcessingBlock {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    List<Middleware> middlewares;

    EventType eventType;

    public ProcessingBlock(List<Middleware> middlewares, EventType eventType) {
        this.middlewares = middlewares;
        this.eventType = eventType;
    }

    public List<Middleware> getMiddlewares() {
        return middlewares;
    }

    public void setMiddlewares(List<Middleware> middlewares) {
        this.middlewares = middlewares;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
