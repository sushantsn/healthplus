package com.app.customValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsINTelephoneMatchingValidator implements ConstraintValidator<INTelephone, Object> {
    @Override
    public void initialize(INTelephone bgTelephone) {
    }

    @Override
    public boolean isValid(Object bgTelephone, ConstraintValidatorContext constraintValidatorContext) {
        Pattern p = Pattern.compile("^[6-9]\\d{9}$");
        Matcher m = p.matcher(bgTelephone.toString());

        return m.find();
    }
}
