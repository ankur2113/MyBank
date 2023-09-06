package com.reset.MyBank.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reset.MyBank.persistencemodel.CustomerAccountTransaction;

@Repository
public interface CustomerAccountTransactionRepository extends JpaRepository<CustomerAccountTransaction, UUID>{

}
