package com.botconstructor.contract.utils;

import com.botconstructor.model.middleware.Middleware;

import java.util.List;

public class Middlewares {
    private Middlewares() { }

    public static boolean verifyOrders(List<Middleware> middlewares) {
        if (middlewares
                .stream()
                .mapToInt(Middleware::getOrderValue)
                .max()
                .orElseThrow() != middlewares.size()) {
            return false;
        }

        for (var i = 0; i < middlewares.size() - 1; i++) {
            var current = middlewares.get(i).getOrderValue();

            var next = middlewares.get(i + 1).getOrderValue();

            if (current + 1 != next) {
                return false;
            }
        }

        return true;
    }
}
