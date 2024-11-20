package com.botconstructor.model.processingblock;

import com.botconstructor.model.event.EventType;
import com.botconstructor.model.middleware.Middleware;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class ProcessingBlock implements Cloneable {
    @OneToMany
    List<Middleware> middlewares;
    EventType eventType;

    String name;
    @Id
    @GeneratedValue
    private int id;

    public ProcessingBlock(List<Middleware> middlewares, EventType eventType, String name, int id) {
        this.middlewares = middlewares;
        this.eventType = eventType;
        this.name = name;
        this.id = id;
    }

    public ProcessingBlock(List<Middleware> middlewares, EventType eventType, String name) {
        this.middlewares = middlewares;
        this.eventType = eventType;
        this.name = name;
    }

    protected ProcessingBlock() {

    }

    public List<Middleware> getMiddlewares() {
        return middlewares;
    }

    public void setMiddlewares(List<Middleware> middlewares) {
        this.middlewares = middlewares;
    }

    public void addMiddleware(Middleware middleware) {
        this.middlewares.add(middleware);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
