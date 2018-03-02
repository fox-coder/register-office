package com.fox.registeroffice.service;

import com.fox.registeroffice.converter.ClientConverter;
import com.fox.registeroffice.domain.Client;
import com.fox.registeroffice.dto.ClientDto;
import com.fox.registeroffice.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientConverter clientConverter) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
    }

    @Transactional
    @Override
    public void saveClient(ClientDto clientDto) {
        log.info(clientDto.toString());
        clientRepository.save(clientConverter.dtoToEntity(clientDto));
    }

    @Override
    public ClientDto getClient(Long clientId) {
        Client client = clientRepository.findOne(clientId);
        if (client == null) {
            throw new IllegalArgumentException("No such client!");
        }
        return clientConverter.entityToDto(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.delete(clientId);
    }

    @Override
    public ClientDto findClient(Long clientId) {
        return clientConverter.entityToDto(clientRepository.findOne(clientId));
    }

    @Override
    public List<ClientDto> findClients(String searchString) {
        List<Client> clients = clientRepository.findClientsByMobile(searchString);
        return CollectionUtils.isEmpty(clients)? new ArrayList<>() : clientConverter.entityListToDtoList(clients);

    }


}
