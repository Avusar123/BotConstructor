package com.botconstructor.dto.converter.middleware.action;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.data.middleware.impl.action.impl.SendMessageActionDto;
import com.botconstructor.model.middleware.impl.action.impl.SendMessageAction;
import org.springframework.stereotype.Component;

@Component
public class SendMessageActionDtoConverter implements DtoConverter<SendMessageAction, SendMessageActionDto> {
    @Override
    public SendMessageActionDto toDto(SendMessageAction sendMessageAction) {
        return new SendMessageActionDto(
                sendMessageAction.getOrderValue(),
                sendMessageAction.getId(),
                sendMessageAction.getName(),
                sendMessageAction.getMessage(),
                sendMessageAction.getChatId()
        );
    }

    @Override
    public SendMessageAction fromDto(SendMessageActionDto sendMessageActionDto) {
        return new SendMessageAction(
                sendMessageActionDto.getOrder(),
                sendMessageActionDto.getMessage(),
                sendMessageActionDto.getChatId(),
                sendMessageActionDto.getName()
        );
    }
}
