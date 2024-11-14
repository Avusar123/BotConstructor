package com.botconstructor.hosting.testdata.middleware;

import com.botconstructor.model.middleware.Middleware;

public class TestMiddleware extends Middleware {
    private int testValue;

    public TestMiddleware(int testValue, int order) {
        super(order);
        this.testValue = testValue;
    }

    public int getTestValue() {
        return testValue;
    }

    public void setTestValue(int testValue) {
        this.testValue = testValue;
    }
}
