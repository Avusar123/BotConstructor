package com.botconstructor.contract.handler.action;

import com.botconstructor.model.action.Action;

public interface ActionHandler<T extends Action> {
    void Act(T action);
}
