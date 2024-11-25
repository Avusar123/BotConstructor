package com.botconstructor.model.validationutil;

import java.util.*;

public class Validations {
    private Validations() {

    }

    public static ValidationResult isValid(List<Validatable> validatables) {
        var context = new ArrayList<Map<String, Integer>>();

        for (int i = 0; i < validatables.size(); i++) {
            context.add(new HashMap<>());
        }

        for (int i = 0; i < validatables.size(); i++) {
            var validator = new Validator(validatables, i, context, "Ошибка валидации!");

            var validatable = validatables.get(i);

            validator = validatable.validator(validator).withoutConditions();

            if (!validator.isValid()) {
                return new ValidationResult(validator.getErrorMessage(), false);
            }
        }

        return new ValidationResult(null, true);
    }
}
