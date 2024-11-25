package com.botconstructor.model.validationutil;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Validator {

    private final List<Validatable> validatables;
    private final int currentIndex;
    private final List<Map<String, Integer>> validationContext;
    private boolean valid = true;
    private String errorMessage;
    private boolean inCondition = true;


    public Validator(
            List<Validatable> validatables,
            int currentIndex,
            List<Map<String, Integer>> validationContext,
            String errorMessage) {
        this.validatables = validatables;

        this.currentIndex = currentIndex;
        this.validationContext = validationContext;
        this.errorMessage = errorMessage;
    }

    private Validator(
            List<Validatable> validatables,
            int currentIndex,
            List<Map<String, Integer>> validationContext,
            String errorMessage,
            boolean valid,
            boolean inCondition) {
        this(validatables, currentIndex, validationContext, errorMessage);
        this.inCondition = inCondition;
        this.valid = valid;
    }

    private Validator(
            List<Validatable> validatables,
            int currentIndex,
            List<Map<String, Integer>> validationContext,
            String errorMessage,
            boolean valid) {
        this(validatables, currentIndex, validationContext, errorMessage);
        this.valid = valid;
    }

    public Validator withExportedValue(String name, Integer value) {
        if (inCondition)
            validationContext.get(currentIndex).put(name, value);

        return this;
    }

    public Validator validateCollection(List<Validatable> validatables) {
        if (inCondition)
            valid &= Validations.isValid(validatables).result();

        return this;
    }

    public Validator withErrorMessage(String message) {
        if (inCondition)
            this.errorMessage = message;

        return this;
    }

    public Validator assure(boolean bool) {
        if (inCondition)
            valid &= bool;

        return this;
    }

    public Validator withoutConditions() {
        return new Validator(
                validatables,
                currentIndex,
                validationContext,
                errorMessage,
                valid);
    }

    public Validator ifIndex(Predicate<Integer> predicate) {
        return new Validator(
                validatables,
                currentIndex,
                validationContext,
                errorMessage,
                valid,
                predicate.test(currentIndex));
    }

    public Validator ifNotFirst() {
        return ifIndex(ind -> ind > 0);
    }

    public Validator ifFirst() {
        return ifIndex(ind -> ind == 0);
    }

    public Validator ifLast() {
        return ifIndex(ind -> ind == validatables.size() - 1);
    }

    public Validator compareWithPrevious(String name,
                                         BiPredicate<Integer, Integer> predicate) {
        if (!inCondition) {
            return this;
        }

        var prevValueMap = validationContext.get(currentIndex - 1);

        var currentValueMap = validationContext.get(currentIndex);

        if (!(prevValueMap.containsKey(name)
                && currentValueMap.containsKey(name)
                && predicate.test(
                                prevValueMap.get(name),
                                currentValueMap.get(name)))) {
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