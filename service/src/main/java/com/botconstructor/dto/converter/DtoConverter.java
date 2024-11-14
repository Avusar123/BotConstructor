package com.botconstructor.dto.converter;

public interface DtoConverter<MType, DtoType> {
    DtoType toDto(MType mType);

    MType fromDto(DtoType dtoType);
}
