package com.fox.registeroffice.util;

import com.fox.registeroffice.domain.AdditionalPhone;
import com.fox.registeroffice.domain.Client;
import com.fox.registeroffice.domain.Passport;

import java.util.Collections;
import java.util.Date;

public final class TestObjectHelper {

    private TestObjectHelper() {
    }

    public static Client getClient() {
        Client testClient = new Client();
        testClient.setMobile("9999999999");
        testClient.setName("Name");
        testClient.setPatronymic("Patronymic");
        testClient.setSurname("Surname");
        return testClient;
    }

    public static Passport getPassport(Client testClient) {
        Passport testPassport = new Passport();
        testPassport.setNumber("123456");
        testPassport.setSeria("1234");
        testPassport.setIssueDate(new Date());
        testPassport.setWhereIssued("issued");
        return testPassport;
    }

    public static AdditionalPhone getAdditionalPhone(Client testClient, String phoneNum) {
        AdditionalPhone phone = new AdditionalPhone();
        phone.setPhone(phoneNum);
        phone.setAdditionalInfo("additional info");
        return phone;
    }

    public static Client createTestClient(Long clientId) {
        Client client = getClient();
        client.setId(clientId);
        Passport passport = getPassport(client);
        passport.setId(1L);
        AdditionalPhone additionalPhone = getAdditionalPhone(client, "9874563214");
        additionalPhone.setId(1L);

        client.setPassports(Collections.singletonList(passport));
        client.setAdditionalPhones(Collections.singletonList(additionalPhone));

        return client;
    }

}
