package com.copperbrass.practice.purchase;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.copperbrass.practice.SiteUser;

public interface PurchaseRepository extends JpaRepository<purchase, Integer> {
	
	@Query("SELECT p FROM purchase p WHERE p.user=?1 ")
	List<purchase> findDeposit1status(SiteUser user);
	
	purchase findByOrderid(String orderid);
	
	Optional<purchase> findById(Integer id);
}
