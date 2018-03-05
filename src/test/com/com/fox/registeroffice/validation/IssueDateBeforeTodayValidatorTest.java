package com.fox.registeroffice.validation;

import com.fox.registeroffice.domain.Passport;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Calendar;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class IssueDateBeforeTodayValidatorTest {

    private static Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidation() throws Exception {
        Passport passport = new Passport();
        passport.setSeria("0145");
        passport.setNumber("014578");
        passport.setWhereIssued("test");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        passport.setIssueDate(calendar.getTime());

        Set<ConstraintViolation<Passport>> validate = validator.validate(passport);
        assertEquals(1, validate.size());
        assertEquals("issueDate", validate.iterator().next().getPropertyPath().toString());
    }
}