package com.fox.registeroffice.repository;

import com.fox.registeroffice.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Long> {

    Client findDistinctClientByMobile(String mobile);

    List<Client> findClientsByMobile(String mobile);

    List<Client> findClientsBySurnameAndName(String surname, String name);

    List<Client> findClientsByMobileAndSurnameAndName(String mobile, String surname, String name);

}
