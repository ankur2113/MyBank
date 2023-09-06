package com.reset.MyBank.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reset.MyBank.persistencemodel.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>{

	Optional<Customer> findByCustomerNumber(Long customerNumber);

}
