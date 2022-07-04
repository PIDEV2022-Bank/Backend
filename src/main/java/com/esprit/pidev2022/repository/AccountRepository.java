package com.esprit.pidev2022.repository;

import com.esprit.pidev2022.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

   Account findByAccountNumber (String accountNumber);
}
