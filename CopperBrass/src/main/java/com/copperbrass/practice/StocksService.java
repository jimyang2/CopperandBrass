package com.copperbrass.practice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StocksService {

	private final StocksRepository stocksRepository;
	
	public List<stocks> mainBestseller1() {
		return this.stocksRepository.findMainBestseller1();
	}
	
	public List<stocks> mainBestseller(String id) {
		return this.stocksRepository.findByIdStartsWith(id);
	}	
	
	public Page<stocks> getList(int page){
		Pageable pageable = PageRequest.of(page,10);
		return this.stocksRepository.findAll(pageable);
	}
	
	public List<stocks> findAll(){
		return this.stocksRepository.findAll();
	}
}
