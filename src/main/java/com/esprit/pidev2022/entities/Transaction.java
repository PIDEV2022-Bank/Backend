package com.esprit.pidev2022.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Trans_Type",discriminatorType = DiscriminatorType.STRING,length=2)
public class Transaction implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long TransactionId;
	private Date transactionDate;
	private double amount;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="account_Code")
	//@JsonIgnore
	private Account account;
	private String description;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Transaction(Date transactionDate, double amount, Account account,String description) {
		super();
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.account = account;
		this.description = description;
	}


}
