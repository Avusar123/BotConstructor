package com.botconstructor.dto.converter.bot;

import com.botconstructor.dto.converter.DtoConverter;
import com.botconstructor.dto.data.BotModelDto;
import com.botconstructor.model.BotModel;
import org.springframework.stereotype.Component;

@Component
public class BotModelConverter implements DtoConverter<BotModel, BotModelDto> {
    @Override
    public BotModelDto toDto(BotModel botModel) {
        return new BotModelDto(botModel.getId(), botModel.getName());
    }

    @Override
    public BotModel fromDto(BotModelDto botModelDto) {
        return new BotModel(botModelDto.getName());
    }
}
