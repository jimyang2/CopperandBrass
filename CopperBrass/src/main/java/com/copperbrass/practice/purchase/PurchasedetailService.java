package com.copperbrass.practice.purchase;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PurchasedetailService {
	
	private final PurchasedetailsRepository purchasedetailsRepository;

	public void saveall(List<purchasedetails> uploadList) {
		this.purchasedetailsRepository.saveAll(uploadList);
		
	}

	public List<purchasedetails> getPurchasedetailsList(Integer id) {
		return this.purchasedetailsRepository.findAllById(id);
		
	}
	
}
