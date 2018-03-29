package com.fox.registeroffice.service.impl;

import com.fox.registeroffice.converter.ClientConverter;
import com.fox.registeroffice.domain.Client;
import com.fox.registeroffice.dto.ClientDto;
import com.fox.registeroffice.repository.ClientRepository;
import com.fox.registeroffice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        Client client = clientConverter.dtoToEntity(clientDto);
        validateClient(client);
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public ClientDto getClient(Long clientId) {
        Client client = clientRepository.findOne(clientId);
        if (client == null) {
            throw new IllegalArgumentException("No such client!");
        }
        return clientConverter.entityToDto(client);
    }

    @Override
    @Transactional
    public void deleteClient(Long clientId) {
        clientRepository.delete(clientId);
    }

    @Override
    @Transactional
    public ClientDto findClient(Long clientId) {
        return clientConverter.entityToDto(clientRepository.findOne(clientId));
    }

    @Override
    @Transactional
    public List<ClientDto> findClients(String searchString) {
        log.info("findClients: search string " + searchString);
        List<Client> clients;
        String[] searchParts = searchString.trim().split(" ");
        if (searchParts.length == 1) {
            clients = clientRepository.findClientsByMobile(searchString);
        } else if (searchParts.length == 2) {
            clients = clientRepository.findClientsBySurnameAndName(searchParts[0], searchParts[1]);
            clients.addAll(clientRepository.findClientsBySurnameAndName(searchParts[1], searchParts[0]));
        } else {
            clients = clientRepository.findClientsByMobileAndSurnameAndName(searchParts[0], searchParts[1], searchParts[2]);
            clients.addAll(clientRepository.findClientsByMobileAndSurnameAndName(searchParts[0], searchParts[2], searchParts[1]));
            clients.addAll(clientRepository.findClientsByMobileAndSurnameAndName(searchParts[1], searchParts[0], searchParts[2]));
            clients.addAll(clientRepository.findClientsByMobileAndSurnameAndName(searchParts[1], searchParts[2], searchParts[0]));
            clients.addAll(clientRepository.findClientsByMobileAndSurnameAndName(searchParts[2], searchParts[1], searchParts[0]));
            clients.addAll(clientRepository.findClientsByMobileAndSurnameAndName(searchParts[2], searchParts[0], searchParts[1]));
        }
        return CollectionUtils.isEmpty(clients)? new ArrayList<>() : clientConverter.entityListToDtoList(clients);

    }



    private static void validateClient(Client client) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        if (!CollectionUtils.isEmpty(violations)) {
            StringBuilder errorBuilder = new StringBuilder();
            for (ConstraintViolation<Client> violation : violations) {
                errorBuilder.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(errorBuilder.toString());
        }
    }


}
