package com.fox.registeroffice.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ClientDto {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String mobile;
    private List<AdditionalPhoneDto> additionalPhones;
    private List<PassportDto> passports;

}
