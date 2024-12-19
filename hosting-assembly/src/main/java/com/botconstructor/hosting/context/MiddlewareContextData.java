package com.botconstructor.hosting.context;

import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.utils.ShouldFormat;
import org.apache.commons.text.StringSubstitutor;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class MiddlewareContextData {
    Map<String, String> data;

    String field = "";

    String baseField = "";

    public MiddlewareContextData() {
        data = new HashMap<>();
    }

    private MiddlewareContextData(Map<String, String> data, String field, String baseField) {
        this.data = data;
        this.field = field;
        this.baseField = baseField;
    }

    public MiddlewareContextData withField(String field) {
        return new MiddlewareContextData(data, field, baseField);
    }

    MiddlewareContextData withBaseField(String baseField) {
        return new MiddlewareContextData(data, field, baseField);
    }

    public void insert(String key, String value) {
        key  = new ArrayList<>(List.of(baseField, field, key))
                .stream()
                .filter(el -> !el.isEmpty())
                .collect(Collectors.joining(":"));

        data.put(key, value);
    }

    public String getValue(String key) {
        if (data.containsKey(baseField + key)) {
            return data.get(baseField + key);
        }

        if (data.containsKey(key)) {
            return data.get(key);
        }

        throw new NoSuchElementException("Контекст не содержит значения по указанному ключу!");
    }

    private Map<String, String> getCleanMap() {
        Map<String, String> resultMap = new HashMap<>();

        for (var el : data.keySet()) {
            if (el.startsWith(baseField + ":")) {
                resultMap.put(el.substring(baseField.length() + 1), data.get(el));
            }

            if (!resultMap.containsKey(el)) {
                resultMap.put(el, data.get(el));
            }
        }

        return resultMap;
    }

    public void formatFields(Middleware middleware) {
        var substitutor = new StringSubstitutor(getCleanMap(), "{", "}");

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
