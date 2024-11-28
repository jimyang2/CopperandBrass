package com.copperbrass.practice.purchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedetailsRepository extends JpaRepository<purchasedetails, Integer>{

	List<purchasedetails> findAllById(Integer id);
}
