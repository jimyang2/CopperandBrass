package com.copperbrass.practice.purchase;

import java.util.List;

import com.copperbrass.practice.stocks;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class purchasedetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		
	private Integer id;

	@ManyToOne
	private stocks stock;
	@ManyToOne
	private purchase purchase;
	
	private Integer count;
	
	private String price;
	
}
