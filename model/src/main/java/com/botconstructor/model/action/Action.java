package com.botconstructor.model.action;
import jakarta.persistence.*;

import java.util.List;
/**
 * Описание действия, совершаемого в ответ на внешний триггер.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Action {
    @Id
    @GeneratedValue
    protected Long id;

    protected String name;

    @ManyToMany
    protected List<Action> childActions;

    public Action(String name) {
        this.name = name;
    }

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
