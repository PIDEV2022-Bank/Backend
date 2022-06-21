package com.esprit.pidev2022.entities;

import java.sql.Timestamp;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Transction_Type",discriminatorType = DiscriminatorType.STRING,length=2)  
public class Transaction {
	@Id @GeneratedValue
	private Long TransactionId;
	private Timestamp transactionDate;
	private double amount;
	@ManyToOne
	@JoinColumn(name="accountcode")
	private Account account;
	@ManyToOne
	private Employee charge;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Transaction(Timestamp transactionDate, double amount, Account account) {
		super();
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.account = account;
	}
	
	
	
	// getter and setter


	public Long getTransactionId() {
		return TransactionId;
	}


	public void setTransactionId(Long transactionId) {
		TransactionId = transactionId;
	}


	public Timestamp getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	

}
