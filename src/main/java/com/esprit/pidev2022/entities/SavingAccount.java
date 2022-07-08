package com.esprit.pidev2022.entities;

import com.esprit.pidev2022.security.model.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Getter
@Setter
@Entity
@DiscriminatorValue("SA")
public class SavingAccount extends Account {

	private float interestRate;

	public SavingAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavingAccount(String accountNumber, Date creationDate, BigDecimal balance, User client, boolean status,
						 float interestRate) {
		super(accountNumber, creationDate, balance,  client, status);
		this.interestRate = interestRate;
	}


}
