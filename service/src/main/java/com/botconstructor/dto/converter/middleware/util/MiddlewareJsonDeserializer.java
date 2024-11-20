package com.botconstructor.dto.converter.middleware.util;

import com.botconstructor.dto.data.middleware.MiddlewareDto;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.Objects;

@JsonComponent
public class MiddlewareJsonDeserializer extends JsonDeserializer<MiddlewareDto> {
    @Autowired
    MiddlewareDtoClassProvider middlewareDtoClassProvider;

    @Override
    public MiddlewareDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.readValueAsTree();

        Objects.requireNonNull(node.get("type"));

        Objects.requireNonNull(node.get("subtype"));

        String type = node.get("type").asText();

        String subType = node.get("subtype").asText();

        var findedClass = middlewareDtoClassProvider.getClass(type, subType);

        return jsonParser.getCodec().treeToValue(node, findedClass);
    }
}
