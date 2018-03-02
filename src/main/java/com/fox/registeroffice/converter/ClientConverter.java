package com.fox.registeroffice.converter;

import com.fox.registeroffice.domain.Client;
import com.fox.registeroffice.dto.ClientDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientConverter {

    ClientDto entityToDto(Client client);

    Client dtoToEntity(ClientDto clientDto);

    List<ClientDto> entityListToDtoList(List<Client> clients);
}
