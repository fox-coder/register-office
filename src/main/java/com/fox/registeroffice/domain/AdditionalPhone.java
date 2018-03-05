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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@Table(name = "AdditionalPhone")
public class AdditionalPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Pattern(regexp = "^[0-9]{6,10}$",
            message = "Номер телефона должен содержать от 6 до 10 цифр")
    private String phone;
    @Size(max = 200, message="Комментарий не более 200 символов")
    private String additionalInfo;
}
