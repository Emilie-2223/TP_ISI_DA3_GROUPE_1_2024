package com.example.TP_ISI2.banque.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomValidators {

    // Validateur pour vérifier si une adresse email est valide
    public static class EmailValidator implements ConstraintValidator<ValidEmail, String> {

        @Override
        public void initialize(ValidEmail constraintAnnotation) {
        }

        @Override
        public boolean isValid(String email, ConstraintValidatorContext context) {
            if (email == null) {
                return false;
            }
            // Expression régulière pour valider une adresse email
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }


}
