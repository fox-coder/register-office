package com.fox.registeroffice.validation;

import com.fox.registeroffice.domain.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Date;
import java.util.Set;

import static com.fox.registeroffice.util.TestObjectHelper.getClient;
import static org.junit.Assert.assertEquals;

@Slf4j
public class AgeRestrictionValidatorTest {

    private static Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidation() throws Exception {
        Client client = getClient();
        client.setBirthday(new Date());

        Set<ConstraintViolation<Client>> validate = validator.validate(client);
        assertEquals(1, validate.size());
        assertEquals("birthday", validate.iterator().next().getPropertyPath().toString());
    }
}