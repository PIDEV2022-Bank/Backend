package com.esprit.pidev2022.services;


import com.esprit.pidev2022.Exception.AccountNotFoundException;
import com.esprit.pidev2022.entities.*;
import com.esprit.pidev2022.repository.AccountRepository;
import com.esprit.pidev2022.repository.TransactionRepository;
import com.esprit.pidev2022.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static int nextAccountNumber = 1125225514;


    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private TransactionRepository transactionRepo;



    @Override
    public SavingAccount createSavingAccount(User user) {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(new BigDecimal(0.0));
        savingAccount.setStatus(false);
        savingAccount.setCreationDate(new Date());
        savingAccount.setAccountNumber("TN01EPARGNE"+String.valueOf(accountGenerator()));
        savingAccount.setClient(user);
        savingAccount.setAccountType("Compte Epargne");

        return accountRepo.save(savingAccount);
    }

    @Override
    public DepositAccount createDepositAccount(User user) {
        DepositAccount depositAccount = new DepositAccount();
        depositAccount.setBalance(new BigDecimal(0.0));
        depositAccount.setCreationDate(new Date());
        depositAccount.setStatus(false);
        depositAccount.setAccountNumber("TN02COURANT"+String.valueOf(accountGenerator()));
        depositAccount.setClient(user);
        depositAccount.setAccountType("Compte Courant");
        return  accountRepo.save(depositAccount);

    }

    public Account updateAccount(Account account){
        return accountRepo.save(account);
    }

    public void deleteAccount(Long id)
    {
        accountRepo.deleteAccountById(id);
    }

    public List<Account> findAllAccount(){

        return (List<Account>) accountRepo.findAll();
    }

    @Override
    public Account findAccountByID(Long idCompte) {
        Optional<Account> account =accountRepo.findById(idCompte);
        if (account ==null) throw new AccountNotFoundException("Compte intouvable");
        return null;
    }


    public List<Account> findClientAccounts(Long clientId){
        List<Account> accounts = accountRepo.findByClientId(clientId);
        return accounts;
    }
    private int accountGenerator() {
        return ++nextAccountNumber;
    }



}