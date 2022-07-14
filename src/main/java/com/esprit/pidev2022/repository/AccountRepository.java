package com.esprit.pidev2022.repository;

import com.esprit.pidev2022.entities.Account;

import com.esprit.pidev2022.entities.SavingAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {

   void deleteAccountById(Long id);

   Account findByAccountNumber (String accountNumber);
}
