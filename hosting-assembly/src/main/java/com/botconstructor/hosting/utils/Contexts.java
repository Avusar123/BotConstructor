package com.botconstructor.hosting.utils;

import com.botconstructor.hosting.context.MiddlewareContextData;

import java.util.List;

public class Contexts {
    private Contexts() {
    }

    public static void serializeListToContextData(
            List<String> list,
            MiddlewareContextData contextData) {
        for (int i = 0; i < list.size(); i++) {
            contextData.insert(String.valueOf(i + 1), list.get(i));
        }

        contextData.insert("size", String.valueOf(list.size()));
    }
}
