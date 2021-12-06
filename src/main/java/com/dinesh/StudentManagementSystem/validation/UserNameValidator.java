package com.dinesh.StudentManagementSystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserName, String> {

    private Pattern compile;

    @Override
    public void initialize(UserName constraintAnnotation) {
        this.compile = Pattern.compile(".+");
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return compile.matcher(value).matches();
    }
}
