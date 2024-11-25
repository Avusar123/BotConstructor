package com.botconstructor.dto.converter.middleware.trigger;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.data.middleware.impl.trigger.impl.CommandMessageTriggerDto;
import com.botconstructor.dto.data.middleware.impl.trigger.impl.EqualityTriggerDto;
import com.botconstructor.model.middleware.impl.trigger.impl.CommandMessageTrigger;
import com.botconstructor.model.middleware.impl.trigger.impl.EqualityTrigger;
import org.springframework.stereotype.Component;

@Component
public class CommandMessageTriggerDtoConverter implements DtoConverter<CommandMessageTrigger, CommandMessageTriggerDto> {
    @Override
    public CommandMessageTriggerDto toDto(CommandMessageTrigger commandMessageTrigger) {
        return new CommandMessageTriggerDto(
                commandMessageTrigger.getOrderValue(),
                commandMessageTrigger.getId(),
                commandMessageTrigger.getName(),
                commandMessageTrigger.getCommandName(),
                commandMessageTrigger.getMaxParamsCount(),
                commandMessageTrigger.getVariableToSave());
    }

    @Override
    public CommandMessageTrigger fromDto(CommandMessageTriggerDto commandMessageTriggerDto) {
        return new CommandMessageTrigger(
                commandMessageTriggerDto.getOrder(),
                commandMessageTriggerDto.getCommandName(),
                commandMessageTriggerDto.getMaxParamsCount(),
                commandMessageTriggerDto.getVariableToSave(),
                commandMessageTriggerDto.getName());
    }
}
