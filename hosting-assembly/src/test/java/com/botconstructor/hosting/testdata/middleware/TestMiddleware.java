package com.botconstructor.hosting.testdata.middleware;

import com.botconstructor.model.middleware.Middleware;

public class TestMiddleware extends Middleware {
    private String testValue;

    public TestMiddleware(String testValue, int order) {
        super(order, testValue);
        this.testValue = testValue;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }
}
