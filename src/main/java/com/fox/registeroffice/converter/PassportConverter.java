package com.fox.registeroffice.converter;

import com.fox.registeroffice.domain.Passport;
import com.fox.registeroffice.dto.PassportDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassportConverter {

    PassportDto entityToDto(Passport passport);

    Passport dtoToEntity(PassportDto passportDto);

}
