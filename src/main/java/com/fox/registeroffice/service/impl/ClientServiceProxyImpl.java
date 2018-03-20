package com.fox.registeroffice.service.impl;

import com.fox.registeroffice.dto.ClientDto;
import com.fox.registeroffice.service.ClientService;
import com.fox.registeroffice.service.ClientServiceProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceProxyImpl implements ClientServiceProxy {

    private final ClientService clientService;

    @Autowired
    public ClientServiceProxyImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    @HystrixCommand(commandKey = "SaveClient")
    public void saveClient(ClientDto clientDto) {
        clientService.saveClient(clientDto);
    }

    @Override
    @HystrixCommand(commandKey = "DeleteClient")
    public void deleteClient(Long clientId) {
        clientService.deleteClient(clientId);
    }

    @Override
    @HystrixCommand(commandKey = "FindOneClient")
    public ClientDto findClient(Long clientId) {
        return clientService.findClient(clientId);
    }

    @Override
    @HystrixCommand(commandKey = "FindClientsList")
    public List<ClientDto> findClients(String searchString) {
        return clientService.findClients(searchString);
    }

}
