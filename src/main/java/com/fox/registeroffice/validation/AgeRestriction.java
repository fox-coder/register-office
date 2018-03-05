package com.fox.registeroffice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AgeRestrictionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeRestriction {

    String message() default "Возраст должен быть в интервале [18;55]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int min() default 18;
    int max() default 55;

}
