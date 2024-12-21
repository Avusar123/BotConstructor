package com.botconstructor.model.validationutil;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Validator<T extends Validatable<T>> {

    private final List<T> validatables;
    private final int currentIndex;
    private boolean valid = true;
    private String errorMessage;
    private boolean inCondition = true;


    public Validator(
            List<T> validatables,
            int currentIndex,
            String errorMessage) {
        this.validatables = validatables;
        this.currentIndex = currentIndex;
        this.errorMessage = errorMessage;
    }

    private Validator(
            List<T> validatables,
            int currentIndex,
            String errorMessage,
            boolean valid,
            boolean inCondition) {
        this(validatables, currentIndex, errorMessage);
        this.inCondition = inCondition;
        this.valid = valid;
    }

    private Validator(
            List<T> validatables,
            int currentIndex,
            String errorMessage,
            boolean valid) {
        this(validatables, currentIndex, errorMessage);
        this.valid = valid;
    }

    public Validator<T> validateCollection(List<T> validatables) {
        if (inCondition)
            valid &= Validations.isValid(validatables).result();

        return this;
    }

    public Validator<T> withErrorMessage(String message) {
        if (inCondition)
            this.errorMessage = message;

        return this;
    }

    public Validator<T> assure(boolean bool) {
        if (inCondition)
            valid &= bool;

        return this;
    }

    public Validator<T> withoutConditions() {
        return new Validator<>(
                validatables,
                currentIndex,
                errorMessage,
                valid);
    }

    public Validator<T> ifIndex(Predicate<Integer> predicate) {
        return new Validator<>(
                validatables,
                currentIndex,
                errorMessage,
                valid,
                predicate.test(currentIndex));
    }

    public Validator<T> ifNotFirst() {
        return ifIndex(ind -> ind > 0);
    }

    public Validator<T> ifFirst() {
        return ifIndex(ind -> ind == 0);
    }

    public Validator<T> ifLast() {
        return ifIndex(ind -> ind == validatables.size() - 1);
    }

    public Validator<T> compareWithPrevious(BiPredicate<T, T> predicate) {
        if (!inCondition || currentIndex == 0) {
            return this;
        }

        var prevElement = validatables.get(currentIndex - 1);

        var currentElement = validatables.get(currentIndex);

        if (!predicate.test(prevElement, currentElement)) {
            valid = false;
        }

        return this;
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}