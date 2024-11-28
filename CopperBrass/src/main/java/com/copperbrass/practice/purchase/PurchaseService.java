package com.copperbrass.practice.purchase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.copperbrass.practice.SiteUser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PurchaseService {
	
	private final PurchaseRepository purchaseRepository;
	
	public purchase create(SiteUser user ) {
		purchase p = new purchase();
		
		// 주문서 이름 만들어야함
		p.setOrderid(user.getUsername()+LocalDateTime.now());
		p.setOrderdatetime(LocalDateTime.now());
		p.setDeposit("0");
		p.setUser(user);	
		
		return this.purchaseRepository.save(p);
		
	}
	
	public void updatePrice(purchase p, Float price) {
		p.setTotalprice(Float.toString(price));
		this.purchaseRepository.save(p);
	}
	
	public List<purchase> findDeposit1status(SiteUser user){
		return this.purchaseRepository.findDeposit1status(user);
	}
	
	public Optional<purchase> getPurchasedetails(Integer id){
		return this.purchaseRepository.findById(id);
	}


	

}
