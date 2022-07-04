package com.esprit.pidev2022.services;

import com.esprit.pidev2022.Exception.AccountNotFoundException;
import com.esprit.pidev2022.Exception.BalanceNotEnoughException;
import com.esprit.pidev2022.entities.*;
import com.esprit.pidev2022.repository.AccountRepository;
import com.esprit.pidev2022.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private TransactionRepository transactionRepo;


    @Override
    public Account getAccount(String accountNumber) {

        Account account=accountRepo.findByAccountNumber(accountNumber);
        if (account ==null) throw new AccountNotFoundException("compte intouvable");
        return null;
    }

    @Override
    public void deposit (String accountNumber,double amount,String description) throws AccountNotFoundException {
        Account account = getAccount(accountNumber);
        DepositTransaction deposit = new DepositTransaction(new Date(),amount,account,
                description);
        transactionRepo.save(deposit);
        account.setBalance(account.getBalance().add(new BigDecimal(amount)));
        accountRepo.save(account);

    }

    @Override
    public void withdrawal (String accountNumber,double amount,String description) throws AccountNotFoundException, BalanceNotEnoughException {
        Account account = getAccount(accountNumber);
        double autorisation=0.0;
        if(account instanceof DepositAccount)
            autorisation=((DepositAccount) account).getAutorisation();
        if ((account.getBalance().add(BigDecimal.valueOf(autorisation)).compareTo(BigDecimal.valueOf(amount))<0))
            throw new BalanceNotEnoughException("Solde insuffisant");
        WithdrawalTransaction   withdrawal = new WithdrawalTransaction(
                new Date(),amount,account, description);
        transactionRepo.save(withdrawal);
        account.setBalance((account.getBalance().subtract(new BigDecimal(amount))));
        accountRepo.save(account);

    }


    @Override
    public void transfer(String accountSource, String accountDestination, double amount) throws AccountNotFoundException, BalanceNotEnoughException {
        withdrawal ( accountSource, amount,"transert vers "+accountDestination);
        deposit ( accountDestination, amount,"transert du accountSource");
    }


    @Override
    public List<Transaction> findTransactionList(String email) {
        return null;
    }
}