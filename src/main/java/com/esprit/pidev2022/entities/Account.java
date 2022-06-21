
 package com.esprit.pidev2022.entities;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Account_Type",discriminatorType = DiscriminatorType.STRING,length=2)
public  class Account {
	
	
	@Id
	private String accountCode;
	private Date creationDate;
	private double balance;
	@ManyToOne
	@JoinColumn (name="clientCode")
	private Client client;

	@OneToMany(mappedBy="account")
	private List <Transaction> transactions;
	
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Account(String accountCode, Date creationDate, double balance, Client client) {
		super();
		this.accountCode = accountCode;
		this.creationDate = creationDate;
		this.balance = balance;
		this.client = client;

	}


	// getter and Setter
	public String getAccountCode() {
		return accountCode;
	}


	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}





	public List<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	

}
