package com.fox.registeroffice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Length(min=4, max=4)
    private String seria;
    @NotNull
    @Length(min=6, max=6)
    private String number;
    @NotNull
    private Date issueDate;
    private String whereIssued;
}
