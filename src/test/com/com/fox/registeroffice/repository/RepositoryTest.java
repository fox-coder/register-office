package com.fox.registeroffice.repository;

import com.fox.registeroffice.domain.AdditionalPhone;
import com.fox.registeroffice.domain.Client;
import com.fox.registeroffice.domain.Passport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static com.fox.registeroffice.util.TestObjectHelper.getAdditionalPhone;
import static com.fox.registeroffice.util.TestObjectHelper.getClient;
import static com.fox.registeroffice.util.TestObjectHelper.getPassport;
import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    public RepositoryTest() {
    }

    private static Client client;
    private static Passport passport;
    private static AdditionalPhone additionalPhone;

    @Before
    public void setUp() throws Exception {
        client = getClient();
        passport = getPassport(client);
        additionalPhone = getAdditionalPhone(client, "9874563214");

        client.setPassports(Collections.singletonList(passport));
        client.setAdditionalPhones(Collections.singletonList(additionalPhone));
        clientRepository.save(client);
    }


    @Test
    public void testClient() throws Exception {

        Client fromDb = clientRepository.findDistinctClientByMobile(client.getMobile());
        assertEquals(client.getMobile(), fromDb.getMobile());
        assertEquals(client.getName(), fromDb.getName());
        assertEquals(client.getPatronymic(), fromDb.getPatronymic());
        assertEquals(client.getSurname(), fromDb.getSurname());

        Passport passportFromDb = fromDb.getPassports().get(0);
        assertEquals(passport.getNumber(), passportFromDb.getNumber());
        assertEquals(passport.getSeria(), passportFromDb.getSeria());
        assertEquals(passport.getIssueDate(), passportFromDb.getIssueDate());
        assertEquals(passport.getWhereIssued(), passportFromDb.getWhereIssued());

        AdditionalPhone phoneFromDb = fromDb.getAdditionalPhones().get(0);
        assertEquals(additionalPhone.getPhone(), phoneFromDb.getPhone());
        assertEquals(additionalPhone.getAdditionalInfo(), phoneFromDb.getAdditionalInfo());

    }
}