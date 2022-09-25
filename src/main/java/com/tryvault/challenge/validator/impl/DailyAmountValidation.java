package com.tryvault.challenge.validator.impl;

import com.tryvault.challenge.model.Transaction;
import com.tryvault.challenge.validator.Validation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DailyAmountValidation implements Validation {

    private final BigDecimal MAXIMUM_LOAD_AMOUNT_PER_DAY = new BigDecimal(5000);

    @Override
    public Optional<String> validate(List<Transaction> customerTransactionListDTO, Transaction newTransaction) {
        BigDecimal loadedToday = customerTransactionListDTO.stream()
                .filter(transaction -> transaction.getTime().toLocalDate().equals(newTransaction.getTime().toLocalDate()))
                .map(Transaction::getLoadAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (loadedToday.add(newTransaction.getLoadAmount()).compareTo(MAXIMUM_LOAD_AMOUNT_PER_DAY) > 0) {
            return Optional.of(String.format("User exceeds the daily maximum load amount of %s.", MAXIMUM_LOAD_AMOUNT_PER_DAY));
        }
        return Optional.empty();
    }
}
