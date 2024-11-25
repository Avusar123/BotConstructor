package com.botconstructor.model.middleware.impl;

import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.validationutil.Validatable;
import com.botconstructor.model.validationutil.Validator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class GroupMiddleware extends Middleware {
    @OneToMany(cascade = CascadeType.ALL)
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
    public Validator validator(Validator validator) {
        return super
                .validator(validator)
                .validateCollection(middlewares
                                    .stream()
                                    .map(mid -> (Validatable)mid)
                                    .toList());
    }
}
