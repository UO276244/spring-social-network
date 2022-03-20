package com.uniovi.sdipractica134.validators;

import com.uniovi.sdipractica134.entities.User;
import com.uniovi.sdipractica134.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class SignUpFormValidator implements Validator {

    private Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private UsersService usersService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * Valida si un usuario se está registrando correctamente
     * @param target usuario que intenta registrarse
     * @param errors errores que comete
     */
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "Error.empty");

        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(user.getUsername()).matches()) {
            errors.rejectValue("username", "Error.signup.username.length");
        }

        if (usersService.getUserByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Error.signup.username.duplicate");
        }

        if (user.getName().length() < 3 || user.getName().length() > 24) {
            errors.rejectValue("name", "Error.signup.name.length");
        }

        if (user.getSurname().length() < 3 || user.getSurname().length() > 24) {
            errors.rejectValue("surname", "Error.signup.surname.length");
        }

        if (user.getPassword().length() < 5 ) {
            errors.rejectValue("password", "Error.signup.password.length");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Error.signup.passwordConfirm.coincidence");
        }

    }
}

