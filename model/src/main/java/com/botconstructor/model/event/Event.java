package com.botconstructor.model.event;


import jakarta.persistence.*;

/**
 * Класс метка для любых событий извне.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Event {
    @Id
    @GeneratedValue
    protected int id;
}
