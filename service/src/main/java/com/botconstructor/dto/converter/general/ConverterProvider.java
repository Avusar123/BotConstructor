package com.botconstructor.dto.converter.general;

import com.botconstructor.dto.converter.DtoConverter;

public interface ConverterProvider {
    <MType, DtoType> DtoConverter<MType, DtoType> getConverter(MType model, Class<DtoType> dtoType);

    <MType, DtoType> DtoConverter<MType, DtoType> getConverter(Class<MType> model, DtoType dtoType);
}
