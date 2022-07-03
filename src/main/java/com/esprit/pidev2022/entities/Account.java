
package com.esprit.pidev2022.entities;
import com.esprit.pidev2022.security.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Acc_Typ",discriminatorType = DiscriminatorType.STRING,length=2)
public  class Account implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String accountNumber;
	private Date creationDate;
	private BigDecimal balance;
	boolean status;
	@ManyToOne
	@JoinColumn (name="clientCode")
	private User client;

	@OneToMany(mappedBy="account" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List <Transaction> transactions;


	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Account(String accountNumber, Date creationDate, BigDecimal balance, User client, boolean status) {
		super();
		this.accountNumber = accountNumber;
		this.creationDate = creationDate;
		this.balance = balance;
		this.client = client;
		this.status = status;
	}


}
