package com.botconstructor.model.middleware.impl;

import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.validationutil.Validatable;
import com.botconstructor.model.validationutil.Validator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class GroupMiddleware extends Middleware {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Middleware> middlewares;

    public GroupMiddleware(int orderValue, String name, List<Middleware> middlewares) {
        super(orderValue, name);
        this.middlewares = middlewares;
    }

    protected GroupMiddleware() {

    }

    public List<Middleware> getMiddlewares() {
        return middlewares;
    }

    public void setMiddlewares(List<Middleware> middlewares) {
        this.middlewares = middlewares;
    }

    @Override
    public Middleware clone() {
        var middleware = (GroupMiddleware) super.clone();

        middleware.middlewares = middlewares.stream().map(Middleware::clone).toList();

        return middleware;
    }

    @Override
    public Validator<Middleware> validator(Validator<Middleware> validator) {
        return super
                .validator(validator)
                .withoutConditions()
                .validateCollection(middlewares
                        .stream()
                        .toList());
    }
}
