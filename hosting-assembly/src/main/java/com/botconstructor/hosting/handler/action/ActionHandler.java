package com.botconstructor.hosting.handler.action;

import com.botconstructor.model.middleware.impl.action.Action;

public interface ActionHandler<T extends Action> {
    void Act(T action);
}
