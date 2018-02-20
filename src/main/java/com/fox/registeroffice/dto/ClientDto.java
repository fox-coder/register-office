package com.fox.registeroffice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClientDto {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String mobile;
    private List<AdditionalPhoneDto> additionalPhones;
    private List<PassportDto> passports;

}
