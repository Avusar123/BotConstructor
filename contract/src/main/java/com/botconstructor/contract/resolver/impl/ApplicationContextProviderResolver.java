package com.botconstructor.contract.resolver.impl;

import com.botconstructor.contract.provider.Provider;
import com.botconstructor.contract.resolver.ProviderResolver;
import com.botconstructor.model.configuration.ProviderConfigType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProviderResolver implements ProviderResolver {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Provider resolve(ProviderConfigType configType) {
        var beans = applicationContext.getBeansOfType(Provider.class);

        for (var bean : beans.values()) {
            if (bean.toConfigType() == configType) {
                return bean;
            }
        }

        throw new IllegalArgumentException("Указанный провайдер не найден!");
    }
}
