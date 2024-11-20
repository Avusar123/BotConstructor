package com.botconstructor.dto.converter.middleware.util;

import com.botconstructor.dto.data.middleware.MiddlewareDto;

public interface MiddlewareDtoClassProvider {
    Class<? extends MiddlewareDto> getClass(String type, String subtype);
}
