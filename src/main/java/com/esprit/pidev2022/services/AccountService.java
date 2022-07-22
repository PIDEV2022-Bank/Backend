package com.esprit.pidev2022.services;

import com.esprit.pidev2022.entities.Account;
import com.esprit.pidev2022.entities.DepositAccount;
import com.esprit.pidev2022.entities.SavingAccount;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface AccountService {

    SavingAccount createSavingAccount(com.esprit.pidev2022.security.model.User user);
   // DepositAccount createDepositAccount(User user);

    DepositAccount createDepositAccount (com.esprit.pidev2022.security.model.User user);

    Account updateAccount(Account account);

    void deleteAccount(Long id);

    List<Account> findAllAccount();

    Account findAccountByID (Long idCompte);
    List<Account> findClientAccounts(Long clientId);



}
