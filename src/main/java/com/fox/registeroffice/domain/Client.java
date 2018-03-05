package com.fox.registeroffice.domain;

import com.fox.registeroffice.validation.AgeRestriction;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message="Имя должно быть задано")
    @Pattern(regexp = "^[а-яА-я\\s-]{2,25}$",
            message = "Имя должно содержать русские символы, пробел и дефис, от 2 до 25 символов")
    private String name;
    @NotNull(message="Фамилия должна быть задана")
    @Pattern(regexp = "^[а-яА-я\\s-]{2,25}$",
            message = "Фамилия должна русские символы, пробел и дефис, от 2 до 25 символов")
    private String surname;
    @NotNull(message="Отчество должна быть задано")
    @Pattern(regexp = "^[а-яА-я\\s-]{2,25}$",
            message = "Отчество должно русские символы, пробел и дефис, от 2 до 25 символов")
    private String patronymic;

    @NotNull(message="Дата рождения должна быть задана")
    @AgeRestriction(message = "Допустимый возраст от 18 до 55 лет")
    private Date birthday;

    @NotNull(message="Телефон должен быть задан")
    @Pattern(regexp = "^9[0-9]{9}$",
            message = "Номер телефона должен начинаться с 9 и содержать 10 цифр")
    private String mobile;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="client_id")
    @Valid
    private List<AdditionalPhone> additionalPhones;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="client_id")
    @Valid
    private List<Passport> passports;
}
