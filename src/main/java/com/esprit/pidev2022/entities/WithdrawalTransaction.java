package com.esprit.pidev2022.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("WI")
public class WithdrawalTransaction extends Transaction {

	public WithdrawalTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WithdrawalTransaction(Date transactionDate, double amount, Account account, String description) {
		super(transactionDate,  amount,  account, description);
		// TODO Auto-generated constructor stub
	}


}
