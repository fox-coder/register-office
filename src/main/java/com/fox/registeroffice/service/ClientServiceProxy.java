package com.fox.registeroffice.service;

import com.fox.registeroffice.dto.ClientDto;

import java.util.List;

public interface ClientServiceProxy {

    void saveClient(ClientDto clientDto);

    void deleteClient(Long clientId);

    ClientDto findClient(Long clientId);

    List<ClientDto> findClients(String searchString);
}
