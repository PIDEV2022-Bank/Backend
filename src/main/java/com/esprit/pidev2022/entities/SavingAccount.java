package com.esprit.pidev2022.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SEV")
public class SavingAccount extends Account {
	
	private float interestRate;

	public SavingAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavingAccount(String accountCode, Date creationDate, double balance, Client client,
			float interestRate) {
		super(accountCode, creationDate, balance, client);
		this.interestRate = interestRate;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	
	
	
	

}
