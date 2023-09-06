package com.reset.MyBank.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reset.MyBank.persistencemodel.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID>{

	Optional<List<Transaction>> findByAccountNumber(Long accountNumber);

}
