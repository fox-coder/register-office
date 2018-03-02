package com.fox.registeroffice.converter;

import com.fox.registeroffice.domain.AdditionalPhone;
import com.fox.registeroffice.dto.AdditionalPhoneDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdditionalPhoneConverter {

    AdditionalPhoneDto entityToDto(AdditionalPhone additionalPhone);

    AdditionalPhoneDto dtoToEntity(AdditionalPhoneDto additionalPhoneDto);

}
