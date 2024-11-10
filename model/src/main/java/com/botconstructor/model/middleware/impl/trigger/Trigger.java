package com.botconstructor.model.middleware.impl.trigger;

import com.botconstructor.model.middleware.Middleware;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Trigger extends Middleware {

    public Trigger(int order) {
        super(order);
    }
}
