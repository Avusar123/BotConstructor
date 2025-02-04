package com.botconstructor.hosting.context;

import com.botconstructor.hosting.provider.Provider;
import com.botconstructor.hosting.resolver.GenericResolver;
import com.botconstructor.model.middleware.Middleware;
import com.botconstructor.model.validationutil.Validatable;
import com.botconstructor.model.validationutil.Validations;

import java.util.Comparator;
import java.util.List;

public class MiddlewareContext {
    private final GenericResolver genericResolver;
    private final List<Middleware> middlewares;
    private final MiddlewareContextData middlewareContextData;
    private final Provider<?> provider;
    private int next;

    MiddlewareContext(GenericResolver genericResolver, List<Middleware> middlewares, MiddlewareContextData middlewareContextData, Provider<?> provider) {
        this.genericResolver = genericResolver;
        this.middlewares = middlewares
                .stream()
                .sorted(Comparator.comparingInt(Middleware::getOrderValue))
                .toList();

        var validResult = Validations.isValid(middlewares.stream().toList());

        if (!validResult.result()) {
            throw new IllegalArgumentException(validResult.message());
        }

        this.middlewareContextData = middlewareContextData;
        this.provider = provider;
        reset();
    }

    public void reset() {
        next = 1;
    }

    public boolean hasNext() {
        return next < middlewares.size() + 1;
    }

    public void next() {
        var middleware = middlewares.get(next - 1);
        middlewareContextData.formatFields(middleware);
        genericResolver.resolveHandler(middleware).act(middleware, this, provider);
        next++;
    }

    public void end() {
        next = middlewares.size() + 1;
    }

    public void goTo(int order) {
        if (middlewares.size() < order - 1 || order <= 0) {
            throw new IllegalArgumentException("Не найден указанный слой");
        }

        next = order;
    }

    public MiddlewareContext createSubContext(List<Middleware> middlewares, String field) {
        return new MiddlewareContext(genericResolver,
                middlewares,
                middlewareContextData.withBaseField(field),
                provider);
    }

    public MiddlewareContextData getContextData() {
        return middlewareContextData;
    }
}
