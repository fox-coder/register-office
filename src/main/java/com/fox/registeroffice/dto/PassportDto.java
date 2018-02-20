package com.fox.registeroffice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PassportDto {

    private Long id;
    private String seria;
    private String number;
    private Date issueDate;
    private String whereIssued;
}
