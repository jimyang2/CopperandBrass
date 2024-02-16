package com.copperbrass.practice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	final private StocksService stocksService;
	
	@GetMapping("/copperbrass")
	public String hello(Model model) {
		
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
		
		return "main";
	}
	
	@GetMapping("/copperbrass/about")
	public String aboutPage(Model model) {
		

		return "about";
	}
	
	@GetMapping("/copperbrass/shop")
	public String aboutShop(Model model, @RequestParam(value="page",defaultValue = "0") int page) {
		Page<stocks> paging = this.stocksService.getList(page);
		model.addAttribute("paging",paging);
		return "shop";
	}	
		
	@GetMapping("/copperbrass/contact")
	public String aboutContact(Model model) {
		List<stocks> stocks = this.stocksService.findAll();
		
		model.addAttribute("stocks",stocks);

		return "contact";
	}		
	
}
