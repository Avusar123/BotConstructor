package com.botconstructor.model.trigger;

import com.botconstructor.model.action.Action;

import java.util.List;

/**
 * Описание триггера, вызывающего действия при возникновении события извне.
 */
public abstract class Trigger {
    protected List<Trigger> triggers;
    protected List<Action> actions;
    protected String name;

    public Trigger(List<Trigger> triggers, List<Action> actions, String name) {
        this.triggers = triggers;
        this.actions = actions;
        this.name = name;
    }

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
}
