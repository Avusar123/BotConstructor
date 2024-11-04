package com.botconstructor.model.data.trigger;

import com.botconstructor.model.data.action.Action;
import com.botconstructor.model.data.event.Event;
import jakarta.persistence.*;

import java.util.List;

/**
 * Описание триггера, вызывающего действия при возникновении события извне.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Trigger {
    @ManyToMany
    protected List<Trigger> triggers;

    @OneToMany
    protected List<Action> actions;

    protected String name;

    @Id
    @GeneratedValue
    protected Integer id;

    public Trigger(List<Trigger> triggers, List<Action> actions, String name) {
        this.triggers = triggers;
        this.actions = actions;
        this.name = name;
    }

    public abstract boolean canProcessEvent(Event ev);

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
