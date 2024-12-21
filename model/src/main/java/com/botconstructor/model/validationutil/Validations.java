package com.botconstructor.model.validationutil;

import java.util.*;

public class Validations {
    private Validations() {

    }

    public static <T extends Validatable<T>> ValidationResult isValid(List<T> validatables) {
        for (int i = 0; i < validatables.size(); i++) {
            Validator<T> validator = new Validator<>(validatables, i, "Ошибка валидации!");

            var validatable = validatables.get(i);

            validator = validatable.validator(validator).withoutConditions();

            if (!validator.isValid()) {
                return new ValidationResult(validator.getErrorMessage(), false);
            }
        }

        return new ValidationResult(null, true);
    }
}
