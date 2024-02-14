package com.copperbrass.practice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StocksRepository extends JpaRepository<stocks, Integer> {

	@Query("SELECT s FROM stocks s WHERE s.id LIKE '%main1%'")
	List<stocks> findMainBestseller1();
	
	List<stocks> findByIdStartsWith(String id);
}


//https://www.baeldung.com/spring-jpa-like-queries     >>> like 쿼리 예문