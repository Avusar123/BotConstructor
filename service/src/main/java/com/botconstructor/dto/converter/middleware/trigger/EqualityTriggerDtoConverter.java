package com.botconstructor.dto.converter.middleware.trigger;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.data.middleware.impl.trigger.impl.EqualityTriggerDto;
import com.botconstructor.model.middleware.impl.trigger.impl.EqualityTrigger;
import org.springframework.stereotype.Component;

@Component
public class EqualityTriggerDtoConverter implements DtoConverter<EqualityTrigger, EqualityTriggerDto> {
    @Override
    public EqualityTriggerDto toDto(EqualityTrigger equalityTrigger) {
        return new EqualityTriggerDto(equalityTrigger.getOrderValue(),
                equalityTrigger.getId(),
                equalityTrigger.getFirstValue(),
                equalityTrigger.getLastValue(),
                equalityTrigger.getName(),
                equalityTrigger.isInverted());
    }

    @Override
    public EqualityTrigger fromDto(EqualityTriggerDto equalityTriggerDto) {
        return new EqualityTrigger(equalityTriggerDto.getOrder(),
                equalityTriggerDto.getFirstValue(),
                equalityTriggerDto.getLastValue(),
                equalityTriggerDto.getName(),
                equalityTriggerDto.isInverted());
    }
}
