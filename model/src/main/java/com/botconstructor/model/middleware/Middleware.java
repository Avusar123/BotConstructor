package com.botconstructor.model.middleware;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Middleware {
    @Id
    @GeneratedValue
    protected int id;

    protected String name;

    protected int order;

    public Middleware(int order) {
        setOrder(order);
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        if (order <= 0) {
            throw new IllegalArgumentException("Порядок не может быть меньше 1");
        }

        this.order = order;
    }
}
