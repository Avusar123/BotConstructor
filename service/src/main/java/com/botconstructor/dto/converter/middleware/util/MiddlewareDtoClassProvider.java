package com.botconstructor.dto.converter.middleware.util;

import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.botconstructor.model.middleware.Middleware;

import java.util.Map;

public interface MiddlewareDtoClassProvider {
    Class<? extends MiddlewareDto> getClass(String type, String subtype);
}
