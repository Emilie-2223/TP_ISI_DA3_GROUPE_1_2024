package com.example.TP_ISI2.banque.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomValidators.EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {

    String message() default "Adresse email invalide";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

