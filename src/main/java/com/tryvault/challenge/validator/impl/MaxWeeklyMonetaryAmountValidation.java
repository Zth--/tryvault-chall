package com.tryvault.challenge.validator.impl;

import com.tryvault.challenge.model.Transaction;
import com.tryvault.challenge.validator.Validation;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.TemporalAdjusters.previousOrSame;


public class MaxWeeklyMonetaryAmountValidation implements Validation {

    private final BigDecimal MAXIMUM_LOAD_PER_WEEK = new BigDecimal(20000);

    @Override
    public Optional<String> validate(List<Transaction> customerTransactionListDTO, Transaction newTransaction) {
        LocalDate firstDayOfThisWeek = LocalDate.from(newTransaction.getTime().with(previousOrSame(DayOfWeek.MONDAY)));

        BigDecimal loadedToday = customerTransactionListDTO.stream()
                .filter(transaction -> transaction.getTime().toLocalDate().isAfter(firstDayOfThisWeek))
                .map(Transaction::getLoadAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (loadedToday.add(newTransaction.getLoadAmount()).compareTo(MAXIMUM_LOAD_PER_WEEK) > 0) {
            return Optional.of(String.format("User exceeds the weekly maximum load amount of %s.", MAXIMUM_LOAD_PER_WEEK));
        }
        return Optional.empty();
    }
}
