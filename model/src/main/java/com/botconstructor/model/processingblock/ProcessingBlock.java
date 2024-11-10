package com.botconstructor.model.processingblock;

import com.botconstructor.model.event.EventType;
import com.botconstructor.model.middleware.Middleware;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.lang.annotation.Inherited;
import java.util.List;

@Entity
public class ProcessingBlock {
    List<Middleware> middlewares;

    EventType eventType;

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
}
