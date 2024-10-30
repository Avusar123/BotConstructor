package com.botconstructor.model.action;

import java.util.List;

/**
 * Описание действия, совершаемого в ответ на внешний триггер.
 */
public abstract class Action {
    protected String name;
    protected List<Action> childActions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getActions() {
        return childActions;
    }

    public void setActions(List<Action> childActions) {
        this.childActions = childActions;
    }

    public void addAction(Action action) {
        childActions.add(action);
    }
}
