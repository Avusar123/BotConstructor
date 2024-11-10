package com.botconstructor.contract.context;

import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.utils.ShouldFormat;
import org.apache.commons.text.StringSubstitutor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

    public void insert(String key, String value) {
        if (!Objects.equals(field, "")) {
            key = field + ":" + key;
        }

        data.put(key, value);
    }

    public String getValue(String key) {
        return data.get(key);
    }

    public void formatFields(Middleware middleware) {
        var substitutor = new StringSubstitutor(data, "{", "}");

        Field[] fields = middleware.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ShouldFormat.class)) {
                field.setAccessible(true);
                try {
                    Object fieldValue = field.get(middleware);

                    if (fieldValue instanceof String) {
                        String formattedValue = substitutor.replace(fieldValue);

                        field.set(middleware, formattedValue);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Ошибка форматирования полей модели!");
                } finally {
                    field.setAccessible(false);
                }
            }
        }
    }
}
