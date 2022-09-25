package com.tryvault.challenge.validator;

import com.tryvault.challenge.model.Transaction;

import java.util.List;
import java.util.Optional;

@FunctionalInterface
public interface Validation {
    Optional<String> validate(List<Transaction> customerTransactionListDTO, Transaction newTransaction);
}
