package com.botconstructor.contract.resolver;

import com.botconstructor.contract.provider.Provider;
import com.botconstructor.model.configuration.ProviderConfigType;

public interface ProviderResolver {
    Provider resolve(ProviderConfigType configType);
}
