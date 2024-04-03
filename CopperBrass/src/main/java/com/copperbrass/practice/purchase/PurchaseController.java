package com.copperbrass.practice.purchase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import com.copperbrass.practice.SiteUser;
import com.copperbrass.practice.StocksService;
import com.copperbrass.practice.user.UserCreateForm;
import com.copperbrass.practice.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PurchaseController {

	private final PurchaseService purchaseService;
	private final PurchasedetailService purchasedetailService;
	private final StocksService stocksService;
	private final UserService userservice;
	
	
    @PostMapping("/purchase/purchase")
    public String purchaseItems(HttpServletRequest request) {
    	
    	String[] name_list = request.getParameterValues("name_list"); 	// [0]에만 값이 있음    	
    	String[] testArray = request.getParameterValues("quan");
    	System.out.println(testArray[0]);
    	
    	String[] stocks_names = name_list[0].split(",");
    	
    	// purchase 만들기(임시로 무조건 user가 작성햇다고 판단)
    	Optional<SiteUser> user= userservice.findUserByName("user");
    	
    	SiteUser user2 = new SiteUser();
    	    	
    	List<purchasedetails> uploadList;
    	
    	for(int i=0;i< 1;i++) {
    		
    		purchase p = new purchase();
    		p.setOrderdatetime(LocalDateTime.now());
    		p.setDeposit("0");
    		p.setUser(user2);
    		//p.setTotalprice(); //이거 땜에 마지막으로 주문서를 빼야함 .. 
    		
    		stocksService.findByName(name_list[i]);
    		
    		
    	}
    	//PurchasedetailService.saveall(uploadList);

        return "redirect:/copperbrass";
    }
	
}
