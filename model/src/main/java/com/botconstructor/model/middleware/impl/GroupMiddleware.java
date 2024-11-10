package com.botconstructor.model.middleware.impl;

import com.botconstructor.model.middleware.Middleware;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class GroupMiddleware extends Middleware {
    @OneToMany
    List<Middleware> middlewares;

    public GroupMiddleware(int order) {
        super(order);
    }

    public List<Middleware> getMiddlewares() {
        return middlewares;
    }

    public void setMiddlewares(List<Middleware> middlewares) {
        this.middlewares = middlewares;
    }
}
