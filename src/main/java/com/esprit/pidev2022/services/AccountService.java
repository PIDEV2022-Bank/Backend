package com.esprit.pidev2022.services;

import com.esprit.pidev2022.entities.Account;
import com.esprit.pidev2022.entities.DepositAccount;
import com.esprit.pidev2022.entities.SavingAccount;
import com.esprit.pidev2022.entities.Transaction;

import java.util.List;

public interface AccountService {

    SavingAccount createSavingAccount();
    DepositAccount createDepositAccount();



}
