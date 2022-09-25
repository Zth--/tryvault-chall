package com.tryvault.challenge.service;

import com.tryvault.challenge.exception.RepeatedIdException;
import com.tryvault.challenge.model.dto.TransactionDTO;
import com.tryvault.challenge.model.dto.TransactionResultDTO;

@FunctionalInterface
public interface LoadFundsService {
    TransactionResultDTO execute(TransactionDTO transactionDTO) throws RepeatedIdException;
}
