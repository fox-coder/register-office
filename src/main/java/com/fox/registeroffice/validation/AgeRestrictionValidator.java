package com.fox.registeroffice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Calendar;
import java.util.Date;

public class AgeRestrictionValidator  implements ConstraintValidator<AgeRestriction, Date> {

    private AgeRestriction constraintAnnotation;

    @Override
    public void initialize(AgeRestriction constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        Calendar minYear = Calendar.getInstance();
        minYear.add(Calendar.YEAR, -constraintAnnotation.max());

        Calendar maxYear = Calendar.getInstance();
        maxYear.add(Calendar.YEAR, -constraintAnnotation.min());

        return value.after(minYear.getTime()) && value.before(maxYear.getTime());
    }
}
