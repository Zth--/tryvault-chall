package com.tryvault.challenge.validator.impl;

import com.tryvault.challenge.model.Transaction;
import com.tryvault.challenge.validator.Validation;

import java.util.List;
import java.util.Optional;

public class DailyLoadAmountValidation implements Validation {

    private final int MAXIMUM_LOADS_PER_DAY = 3;

    @Override
    public Optional<String> validate(List<Transaction> customerTransactionListDTO, Transaction newTransaction) {
        long loadsToday = customerTransactionListDTO.stream()
                .filter(transaction -> transaction.getTime().toLocalDate().equals(newTransaction.getTime().toLocalDate()))
                .count();
        if (loadsToday >= MAXIMUM_LOADS_PER_DAY) {
            return Optional.of("User exceeds maximum loads per day!");
        }
        return Optional.empty();
    }
}
