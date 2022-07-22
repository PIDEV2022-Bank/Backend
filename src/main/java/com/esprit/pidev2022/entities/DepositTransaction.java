package com.esprit.pidev2022.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DE")
public class DepositTransaction extends Transaction{

	public DepositTransaction () {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepositTransaction (Date transactionDate, double amount, Account account, String description) {
		super(transactionDate,  amount,  account, description);
		// TODO Auto-generated constructor stub
	}

}