package com.botconstructor.dto.converter.middleware.trigger;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.data.middleware.impl.trigger.impl.TextMessageTriggerDto;
import com.botconstructor.model.middleware.impl.trigger.impl.TextMessageTrigger;
import org.springframework.stereotype.Component;

@Component
public class TextMessageTriggerDtoConverter implements DtoConverter<TextMessageTrigger, TextMessageTriggerDto> {
    @Override
    public TextMessageTriggerDto toDto(TextMessageTrigger textMessageTrigger) {
        return new TextMessageTriggerDto(
                textMessageTrigger.getOrderValue(),
                textMessageTrigger.getId(),
                textMessageTrigger.getName(),
                textMessageTrigger.getRegex()
        );
    }

    @Override
    public TextMessageTrigger fromDto(TextMessageTriggerDto textMessageTriggerDto) {
        return new TextMessageTrigger(
                textMessageTriggerDto.getOrder(),
                textMessageTriggerDto.getRegex(),
                textMessageTriggerDto.getName()
        );
    }
}
