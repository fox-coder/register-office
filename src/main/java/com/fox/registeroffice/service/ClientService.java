package com.fox.registeroffice.service;

import com.fox.registeroffice.dto.ClientDto;

import java.util.List;

public interface ClientService {

    void saveClient(ClientDto clientDto);

    ClientDto getClient(Long clientId);

    void deleteClient(Long clientId);

    ClientDto findClient(Long clientId);

    List<ClientDto> findClients(String searchString);
}
