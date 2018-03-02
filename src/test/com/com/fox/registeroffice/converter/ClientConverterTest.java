package com.fox.registeroffice.converter;

import com.fox.registeroffice.domain.AdditionalPhone;
import com.fox.registeroffice.domain.Client;
import com.fox.registeroffice.domain.Passport;
import com.fox.registeroffice.dto.AdditionalPhoneDto;
import com.fox.registeroffice.dto.ClientDto;
import com.fox.registeroffice.dto.PassportDto;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Date;

import static com.fox.registeroffice.util.TestObjectHelper.createTestClient;
import static org.junit.Assert.assertEquals;

public class ClientConverterTest {

    private ClientConverter converter = Mappers.getMapper(ClientConverter.class);

    @Test
    public void convertToDto() throws Exception {
        Client client = createTestClient(1L);
        ClientDto clientDto = converter.entityToDto(client);

        assertEquals(client.getId(), clientDto.getId());
        assertEquals(client.getName(), clientDto.getName());
        assertEquals(client.getSurname(), clientDto.getSurname());
        assertEquals(client.getPatronymic(), clientDto.getPatronymic());
        assertEquals(client.getMobile(), clientDto.getMobile());

        Passport passport = client.getPassports().get(0);
        PassportDto passportDto = clientDto.getPassports().get(0);
        assertEquals(passport.getId(), passportDto.getId());
        assertEquals(passport.getSeria(), passportDto.getSeria());
        assertEquals(passport.getNumber(),passportDto.getNumber());
        assertEquals(passport.getWhereIssued(), passportDto.getWhereIssued());
        assertEquals(passport.getIssueDate(), passportDto.getIssueDate());

        AdditionalPhone phone = client.getAdditionalPhones().get(0);
        AdditionalPhoneDto phoneDto = clientDto.getAdditionalPhones().get(0);
        assertEquals(phone.getId(), phoneDto.getId());
        assertEquals(phone.getPhone(), phoneDto.getPhone());
        assertEquals(phone.getAdditionalInfo(), phoneDto.getAdditionalInfo());
    }

    @Test
    public void convertFromDto() throws Exception {
        PassportDto passportDto = createPassportDto();
        AdditionalPhoneDto phoneDto = createAdditionalPhoneDto();
        ClientDto clientDto = createClientDto(passportDto, phoneDto);

        Client client = converter.dtoToEntity(clientDto);
        assertEquals(clientDto.getId(), client.getId());
        assertEquals(clientDto.getMobile(), client.getMobile());
        assertEquals(clientDto.getName(), client.getName());
        assertEquals(clientDto.getSurname(), client.getSurname());
        assertEquals(clientDto.getPatronymic(), client.getPatronymic());

        Passport passport = client.getPassports().get(0);
        assertEquals(passportDto.getId(), passport.getId());
        assertEquals(passportDto.getNumber(), passport.getNumber());
        assertEquals(passportDto.getSeria(), passport.getSeria());
        assertEquals(passportDto.getIssueDate(), passport.getIssueDate());
        assertEquals(passportDto.getWhereIssued(), passport.getWhereIssued());

        AdditionalPhone phone = client.getAdditionalPhones().get(0);
        assertEquals(phoneDto.getId(), phone.getId());
        assertEquals(phoneDto.getPhone(), phone.getPhone());
        assertEquals(phoneDto.getAdditionalInfo(), phone.getAdditionalInfo());

    }

    private static PassportDto createPassportDto() {
        PassportDto passportDto = new PassportDto();
        passportDto.setId(1L);
        passportDto.setNumber("1234");
        passportDto.setSeria("147852");
        passportDto.setIssueDate(new Date());
        passportDto.setWhereIssued("whereIssued");
        return passportDto;
    }

    private static AdditionalPhoneDto createAdditionalPhoneDto() {
        AdditionalPhoneDto phoneDto = new AdditionalPhoneDto();
        phoneDto.setId(1L);
        phoneDto.setPhone("9874563210");
        phoneDto.setAdditionalInfo("fgflgkdfl;gkf;");
        return phoneDto;
    }

    private static ClientDto createClientDto(PassportDto passportDto, AdditionalPhoneDto phoneDto) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setName("Name");
        clientDto.setSurname("Surname");
        clientDto.setPatronymic("Patronymic");
        clientDto.setMobile("9874563217");
        clientDto.setAdditionalPhones(Collections.singletonList(phoneDto));
        clientDto.setPassports(Collections.singletonList(passportDto));
        return clientDto;
    }
}