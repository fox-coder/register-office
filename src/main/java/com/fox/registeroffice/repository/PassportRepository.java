package com.fox.registeroffice.repository;

import com.fox.registeroffice.domain.Passport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository  extends CrudRepository<Passport, Long> {

}
