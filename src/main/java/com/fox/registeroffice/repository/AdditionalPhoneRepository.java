package com.fox.registeroffice.repository;

import com.fox.registeroffice.domain.AdditionalPhone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalPhoneRepository  extends CrudRepository<AdditionalPhone, Long> {

}
