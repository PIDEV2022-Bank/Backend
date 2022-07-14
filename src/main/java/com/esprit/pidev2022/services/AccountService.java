package com.esprit.pidev2022.services;

import com.esprit.pidev2022.entities.Account;
import com.esprit.pidev2022.entities.DepositAccount;
import com.esprit.pidev2022.entities.SavingAccount;

import java.util.List;

public interface AccountService {

    SavingAccount createSavingAccount(SavingAccount savingAccount);
    DepositAccount createDepositAccount();
    Account updateAccount(Account account);

    void deleteAccount(Long id);

    List<Account> findAllAccount();

    Account findAccountByID (Long idCompte);



}
