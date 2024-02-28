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
	
	public List<stocks> findAll(){
		return this.stocksRepository.findAll();
	}
	
	public List<stocks> find4ByNum(int num){
		return this.stocksRepository.find4stocksByNum(num);
	}
	public List<stocks> find4ByNum(){
		return this.stocksRepository.find4stocksByNum();
	}
	
	public List<stocks> find4ByNum(String category){
		return this.stocksRepository.find4stocksByNum(category);
	}
	public List<stocks> find4ByNum(String category, int num){
		return this.stocksRepository.find4stocksByNum(category, num);
	}
		
	
	
	public stocks findByNum(int num) {
		return this.stocksRepository.findByNum(num);
	}
}
