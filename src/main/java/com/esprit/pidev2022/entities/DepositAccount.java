package com.esprit.pidev2022.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEP")
public class DepositAccount extends Account {
	
	private double autorisation;
	public DepositAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepositAccount(String accountCode, Date creationDate, double balance, Client client,
			double autorisation) {
		super(accountCode, creationDate, balance, client);
		this.autorisation = autorisation;
	}

	
	// getter and setter
	public double getAutorisation() {
		return autorisation;
	}

	public void setAutorisation(double autorisation) {
		this.autorisation = autorisation;
	}

	
	
	
	
	

}
