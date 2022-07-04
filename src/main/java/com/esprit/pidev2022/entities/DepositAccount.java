package com.esprit.pidev2022.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.esprit.pidev2022.security.model.User;
@Getter
@Setter
@Entity
@DiscriminatorValue("DE")
public class DepositAccount extends Account {

	private double autorisation;
	public DepositAccount() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DepositAccount(String accountNumber, Date creationDate, BigDecimal balance, User client, boolean status,
						  double autorisation) {
		super(accountNumber, creationDate, balance,  client, status);
		this.autorisation = autorisation;
	}


}