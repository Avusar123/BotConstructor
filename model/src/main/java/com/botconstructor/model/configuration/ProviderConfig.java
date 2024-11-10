package com.botconstructor.model.configuration;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ProviderConfig implements ConfigEnumConverter {
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
