package com.reset.MyBank.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reset.MyBank.persistencemodel.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{

	Optional<Account> findByAccountNumber(Long accountNumber);

}
