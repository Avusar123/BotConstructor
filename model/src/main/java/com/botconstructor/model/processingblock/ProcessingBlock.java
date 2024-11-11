package com.botconstructor.model.processingblock;

import com.botconstructor.model.event.EventType;
import com.botconstructor.model.middleware.Middleware;
import jakarta.persistence.*;

import java.lang.annotation.Inherited;
import java.util.List;

@Entity
public class ProcessingBlock implements Cloneable{
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

    @Override
    public ProcessingBlock clone() {
        try {
            ProcessingBlock clone = (ProcessingBlock) super.clone();

            clone.middlewares = middlewares.stream().map(Middleware::clone).toList();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
