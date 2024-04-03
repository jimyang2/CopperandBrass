package com.copperbrass.practice.purchase;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PurchaseService {
	
	private final PurchaseRepository purchaseRepository;
	
}
