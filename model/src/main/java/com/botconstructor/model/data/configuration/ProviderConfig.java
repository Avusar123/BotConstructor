package com.botconstructor.model.data.configuration;


import jakarta.persistence.*;

/**
 * Класс метка для конфигуарции провайдера API бота.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ProviderConfig {
    @Id
    @GeneratedValue
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
