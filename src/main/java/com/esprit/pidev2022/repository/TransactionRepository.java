package com.esprit.pidev2022.repository;


import com.esprit.pidev2022.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findAll();

    List<Transaction> findByAccountAccountNumber(String accountNumber);

    List<Transaction> findByAccountId(Long accountId);
}
