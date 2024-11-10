package com.botconstructor.model.middleware.impl.action;

import com.botconstructor.model.middleware.Middleware;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Action extends Middleware {
    public Action(int order) {
        super(order);
    }
}
