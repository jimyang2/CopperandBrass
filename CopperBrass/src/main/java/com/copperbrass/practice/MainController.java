package com.copperbrass.practice;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	final private StocksService stocksService;
	

	
	@GetMapping("/copperbrass")
	public String hello(Model model, Principal principal) {
		
		List<stocks> bestseller1 = new ArrayList<>();
		bestseller1 = this.stocksService.mainBestseller1();
		
		List<stocks> bestseller2 = new ArrayList<>();
		bestseller2 = this.stocksService.mainBestseller("main2");
		
		List<stocks> bestseller3 = new ArrayList<>();
		bestseller3 = this.stocksService.mainBestseller("main3");
		
		List<stocks> bestseller4 = new ArrayList<>();
		bestseller4 = this.stocksService.mainBestseller("main4");
		
		
		model.addAttribute("bestseller1",bestseller1);
		model.addAttribute("bestseller2",bestseller2);
		model.addAttribute("bestseller3",bestseller3);
		model.addAttribute("bestseller4",bestseller4);		
		model.addAttribute("test","/img/main/bestseller/main_bundle.JPG");
		
		if(principal != null) {
			model.addAttribute("principal",principal);
			System.out.println("미야옹");
			System.out.println(principal);
		}
		
		
		return "main";
	}
	
	@GetMapping("/copperbrass/about")
	public String aboutPage(Model model) {
		

		return "about";
	}
	
	@GetMapping("/copperbrass/shop")
	public String aboutShop(Model model, @RequestParam(value="num", defaultValue="") String category) {
		
		List<stocks> stocks = new ArrayList<>();
		int lastStockNum = 0;
		
		if(category.isBlank()) {
			stocks = stocksService.find4ByNum();
		}else {
			stocks = stocksService.find4ByNum(category);
		}
		
		model.addAttribute("firstStocks",stocks);
		
		if (!stocks.isEmpty()) {
			lastStockNum = stocks.get(stocks.size() - 1).getNum();
		}
		
		model.addAttribute("lastStockNum",lastStockNum);
		
		return "shop";
	}	
		
	@GetMapping("/copperbrass/contact")
	public String aboutContact(Model model) {
		List<stocks> stocks = this.stocksService.findAll();
		
		model.addAttribute("stocks",stocks);

		return "contact";
	}		
	
	@GetMapping("/copperbrass/findAllProductList")
	@ResponseBody
	public List<stocks> findAllProductList(){
		List<stocks> addstocks = new ArrayList<>();
		
		//addstocks = this.stocksService.find4ByNum(6);

		
		return addstocks;// @ResponseBody에 의해 JSONArray 로 응답한다 
	}	
	
	@PostMapping("/copperbrass/findAllProductList")
	@ResponseBody
	public List<stocks> findAllProductListP(@RequestParam Map<String, Object> vo){
		
		List<stocks> addstocks = new ArrayList<>();
		int lastStockNum = Integer.parseInt((String) vo.get("lastStockNum"));
		String category = (String) vo.get("category");
		
		System.out.println("findAllProductListP함수");
		if (category.isBlank()) {
			addstocks = this.stocksService.find4ByNum(lastStockNum);
		}else {
			addstocks = this.stocksService.find4ByNum(category,lastStockNum);
		}
		

		
		return addstocks;// @ResponseBody에 의해 JSONArray 로 응답한다 
	}	
	
	@GetMapping("/copperbrass/shop-details/{number}")
	public String stockDetails(Model model, @PathVariable("number") int number){
		stocks stock = new stocks();
		
		stock = this.stocksService.findByNum(number);
		model.addAttribute("stock",stock);
		
		return "shop-details";// @ResponseBody에 의해 JSONArray 로 응답한다 
	}	
	
	@GetMapping("/copperbrass/policies-shipping")
	public String aboutpoiciesShipping(Model model) {
		

		return "policies-shipping";
	}	
	
	@GetMapping("/copperbrass/view-cart")
	public String aboutViewCart(Model model) {
		

		return "view-cart";
	}		
}
