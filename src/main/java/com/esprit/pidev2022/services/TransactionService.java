package com.esprit.pidev2022.services;

import com.esprit.pidev2022.Exception.AccountNotFoundException;
import com.esprit.pidev2022.Exception.BalanceNotEnoughException;
import com.esprit.pidev2022.entities.Account;
import com.esprit.pidev2022.entities.Transaction;

import java.util.List;

public interface TransactionService {

    Account getAccount(String accountNumber);
    void deposit(String accountNumber, double amount, String description) throws AccountNotFoundException;
    void withdrawal(String accountNumber, double amount, String description) throws AccountNotFoundException, BalanceNotEnoughException;

    void transfer (String accountSource, String accountDestination, double amount) throws AccountNotFoundException, BalanceNotEnoughException;

    public List<Transaction> findAccountTransaction(Long accountId);







}
