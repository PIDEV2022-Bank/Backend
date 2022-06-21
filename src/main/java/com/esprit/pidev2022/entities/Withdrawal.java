package com.esprit.pidev2022.entities;

import java.sql.Timestamp;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("W")
public class Withdrawal extends Transaction {

	public Withdrawal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Withdrawal(Timestamp transactionDate, double amount, Account account) {
		super(transactionDate, amount, account);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
