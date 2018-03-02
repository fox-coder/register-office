package com.fox.registeroffice.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PassportDto {

    private Long id;
    private String seria;
    private String number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;
    private String whereIssued;
}
