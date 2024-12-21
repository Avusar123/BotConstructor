package com.botconstructor.model.validationutil;

public interface Validatable<U extends Validatable<U>> {
    Validator<U> validator(Validator<U> validator);
}
