package com.esprit.pidev2022.entities;

import java.sql.Timestamp;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("D")
public class Deposit extends Transaction{

	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Deposit(Timestamp transactionDate, double amount, Account account) {
		super(transactionDate, amount, account);
		// TODO Auto-generated constructor stub
	}

}
