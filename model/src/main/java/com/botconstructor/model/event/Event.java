package com.botconstructor.model.event;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Event {
    @Id
    @GeneratedValue
    protected int id;

    public abstract EventType getType();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
