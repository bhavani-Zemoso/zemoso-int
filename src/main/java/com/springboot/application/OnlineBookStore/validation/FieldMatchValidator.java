package com.springboot.application.OnlineBookStore.validation;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, ConstraintValidatorContext constraintValidatorContext) {

        boolean valid = true;
        try
        {
            final Object firstObject = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
            final Object secondObject = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

            valid =  firstObject == null && secondObject == null || firstObject != null && firstObject.equals(secondObject);

        }

        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }

        if(!valid)
        {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
