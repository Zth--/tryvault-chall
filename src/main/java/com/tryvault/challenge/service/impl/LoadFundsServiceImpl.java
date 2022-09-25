package com.tryvault.challenge.service.impl;

import com.tryvault.challenge.exception.RepeatedIdException;
import com.tryvault.challenge.model.Transaction;
import com.tryvault.challenge.model.dto.TransactionDTO;
import com.tryvault.challenge.model.dto.TransactionResultDTO;
import com.tryvault.challenge.repository.TransactionsRepository;
import com.tryvault.challenge.service.LoadFundsService;
import com.tryvault.challenge.validator.Validation;
import com.tryvault.challenge.validator.impl.DailyAmountValidation;
import com.tryvault.challenge.validator.impl.DailyLoadAmountValidation;
import com.tryvault.challenge.validator.impl.MaxWeeklyMonetaryAmountValidation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoadFundsServiceImpl implements LoadFundsService {

    TransactionsRepository transactionsRepository;
    final List<Validation> validations = new ArrayList<>();

    LoadFundsServiceImpl(TransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
        validations.add(new DailyAmountValidation());
        validations.add(new DailyLoadAmountValidation());
        validations.add(new MaxWeeklyMonetaryAmountValidation());
    }

    @Override
    public TransactionResultDTO execute(TransactionDTO transactionDTO) throws RepeatedIdException {
        if (transactionsRepository.findByIdAndCustomerId(transactionDTO.getId(), Integer.valueOf(transactionDTO.getCustomerId())).isPresent()) {
            throw new RepeatedIdException(String.format("Id %s already in database for customer %s", transactionDTO.getId(), transactionDTO.getCustomerId()));
        }
        List<Transaction> transactions = transactionsRepository.findAllByCustomerIdAndAcceptedTrue(Integer.parseInt(transactionDTO.getCustomerId()));
        Transaction newTransaction = Transaction.fromTransactionDTO(transactionDTO);

        boolean accepted = true;
        for (Validation validation : validations) {
            Optional<String> error = validation.validate(transactions, newTransaction);
            if (error.isPresent()) {
                accepted = false;
                break;
            }
        }
        newTransaction.setAccepted(accepted);
        transactionsRepository.save(newTransaction);
        return new TransactionResultDTO(transactionDTO.getId().toString(), transactionDTO.getCustomerId(), accepted);
    }
}
