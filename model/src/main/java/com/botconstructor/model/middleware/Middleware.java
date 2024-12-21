package com.botconstructor.model.middleware;

import com.botconstructor.model.user.OwnedEntity;
import com.botconstructor.model.validationutil.Validatable;
import com.botconstructor.model.validationutil.Validator;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Middleware extends OwnedEntity implements Validatable<Middleware>, Cloneable{
    @Id
    @GeneratedValue
    protected UUID id;

    protected String name;

    protected int orderValue;

    public Middleware(int orderValue, String name) {
        setOrderValue(orderValue);
        this.name = name;
    }

    protected Middleware() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    @Override
    public Validator<Middleware> validator(Validator<Middleware> validator) {
        return validator
                .compareWithPrevious((mid1,mid2) -> mid1.getOrderValue() == mid2.getOrderValue() + 1)
                .withErrorMessage("Порядок не должен прерываться!")
                .ifFirst()
                .assure(orderValue == 1)
                .withErrorMessage("Порядок должен начинаться с 1!");
    }
}
