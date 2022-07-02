package com.esprit.pidev2022.services;


import com.esprit.pidev2022.entities.*;
import com.esprit.pidev2022.repository.AccountRepository;
import com.esprit.pidev2022.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;


@Service
public class AccountServiceImpl implements AccountService {

           private static int nextAccountNumber = 1125225514;


    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private TransactionRepository transactionRepo;

    @Override
    public SavingAccount createSavingAccount() {

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(new BigDecimal(0.0));
        savingAccount.setStatus(false);
        savingAccount.setAccountNumber("TN01S"+String.valueOf(accountGenerator()));
        return null;
    }

        @Override
        public DepositAccount createDepositAccount () {
            DepositAccount depositAccount = new DepositAccount();
            depositAccount.setBalance(new BigDecimal(0.0));
            depositAccount.setStatus(false);
            depositAccount.setAccountNumber("TN02D"+String.valueOf(accountGenerator()));
            accountRepo.save(depositAccount);

            return (DepositAccount)accountRepo.findByAccountNumber(depositAccount.getAccountNumber());
        }


        private int accountGenerator() {
            return ++nextAccountNumber;
        }
    }