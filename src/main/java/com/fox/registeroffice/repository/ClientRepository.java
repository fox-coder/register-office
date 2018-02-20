package com.fox.registeroffice.repository;

import com.fox.registeroffice.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Long> {

    Client findDistinctClientByMobile(String mobile);

}
