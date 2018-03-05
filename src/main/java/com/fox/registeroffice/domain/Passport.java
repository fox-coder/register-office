package com.fox.registeroffice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotNull(message="Серия паспорта должна быть задана")
    @Pattern(regexp = "^[0-9]{4}$",
            message = "Серия паспорта состоит из четырех цифр")
    private String seria;
    @NotNull(message="Номер паспорта должен быть задан")
    @Pattern(regexp = "^[0-9]{6}$",
            message = "Номер паспорта состоит из шести цифр")
    private String number;
    @NotNull
    @Past(message = "Дата выдачи паспорта должна быть в прошлом")
    private Date issueDate;
    @Size(max = 300, message="Поле \"Где выдано\" должно быть не более 300 символов")
    private String whereIssued;
}
