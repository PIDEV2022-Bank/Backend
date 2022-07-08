package com.esprit.pidev2022.entities;

import com.esprit.pidev2022.security.model.User;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idproduct;
	private String name;
	private float price;
	private String description;
	@Enumerated(EnumType.STRING)
	private producttype producttype;

	@ManyToOne
	private User user;
	
	
	

}
