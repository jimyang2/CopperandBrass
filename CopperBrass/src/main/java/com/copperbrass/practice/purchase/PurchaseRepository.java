package com.copperbrass.practice.purchase;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.copperbrass.practice.SiteUser;
import com.copperbrass.practice.stocks;

public interface PurchaseRepository extends JpaRepository<purchase, Integer> {
	
	@Query("SELECT p FROM purchase p WHERE p.user=?1 and deposit='0'")
	Optional<purchase> findDeposit1status(SiteUser user);
}
