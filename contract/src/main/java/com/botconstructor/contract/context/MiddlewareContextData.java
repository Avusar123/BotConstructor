package com.botconstructor.contract.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MiddlewareContextData {
    Map<String, String> data;

    String field = "";

    public MiddlewareContextData() {
        data = new HashMap<>();
    }

    private MiddlewareContextData(Map<String, String> data, String field) {
        this.data = data;
        this.field = field;
    }

    public MiddlewareContextData withField(String field) {
        return new MiddlewareContextData(data, field);
    }

    public void Insert(String key, String value) {
        if (!Objects.equals(field, "")) {
            key = field + ":" + key;
        }

        data.put(key, value);
    }

    public String getValue(String key) {
        return data.get(key);
    }
}
