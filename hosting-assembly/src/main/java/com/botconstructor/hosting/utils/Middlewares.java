package com.botconstructor.hosting.utils;

import com.botconstructor.hosting.context.MiddlewareContextData;
import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.middleware.impl.GroupMiddleware;

import java.util.List;

public class Middlewares {
    private Middlewares() {
    }

    public static boolean verifyOrders(List<Middleware> middlewares) {
        if (middlewares
                    .stream()
                    .mapToInt(Middleware::getOrderValue)
                    .max()
                    .orElseThrow() != middlewares.size()) {
            return false;
        }

        for (var i = 0; i < middlewares.size() - 1; i++) {
            var currentMid = middlewares.get(i);

            if (currentMid instanceof GroupMiddleware) {
                verifyOrders(((GroupMiddleware) currentMid).getMiddlewares());
            }

            var current = currentMid.getOrderValue();

            var next = middlewares.get(i + 1).getOrderValue();

            if (current + 1 != next) {
                return false;
            }
        }

        return true;
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
