package com.botconstructor.hosting.context;

import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.utils.ShouldFormat;
import org.apache.commons.text.StringSubstitutor;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class MiddlewareContextData {
    Map<String, String> data;

    String object = "";

    String baseField = "";

    public MiddlewareContextData() {
        data = new HashMap<>();
    }

    private MiddlewareContextData(Map<String, String> data, String object, String baseField) {
        this.data = data;
        this.object = object;
        this.baseField = baseField;
    }

    public MiddlewareContextData withObject(String object) {
        return new MiddlewareContextData(data, object, baseField);
    }

    MiddlewareContextData withBaseField(String baseField) {
        return new MiddlewareContextData(data, object, combine(this.baseField, baseField));
    }

    private String combine(String... args) {
        return Arrays.stream(args)
                .filter(el -> !el.isEmpty())
                .collect(Collectors.joining(":"));
    }

    public void insert(String key, String value) {
        data.put(combine(baseField, object, key), value);
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

        Map<String, Integer> counter = new HashMap<>();

        for (var el : data.keySet()) {
            var splittedElement = el.split(":");

            var obj = splittedElement[splittedElement.length - 2];

            var field = splittedElement[splittedElement.length - 1];

            var key = obj + ":" + field;

            var currentField = el.replace(key, "");

            if (!(baseField + ":").startsWith(currentField)) {
                continue;
            }

            if (!counter.containsKey(key) || counter.get(key) <= splittedElement.length) {
                resultMap.put(key, data.get(el));

                counter.put(key, splittedElement.length);
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
