package com.tryvault.challenge.repository;

import com.tryvault.challenge.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCustomerIdAndAcceptedTrue(Integer customerId);
    Optional<Transaction> findByIdAndCustomerId(Long id, Integer customerId);
}
