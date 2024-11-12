package com.botconstructor.model.middleware;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Middleware implements Cloneable {
    @Id
    @GeneratedValue
    protected int id;

    protected String name;

    protected int orderValue;

    public Middleware(int orderValue) {
        setOrderValue(orderValue);
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

    public int getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(int orderValue) {
        if (orderValue <= 0) {
            throw new IllegalArgumentException("Порядок не может быть меньше 1");
        }

        this.orderValue = orderValue;
    }

    @Override
    public Middleware clone() {
        try {
            return (Middleware) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
