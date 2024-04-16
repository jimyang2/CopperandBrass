package com.copperbrass.practice.purchase;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.copperbrass.practice.SiteUser;
import com.copperbrass.practice.StocksService;
import com.copperbrass.practice.stocks;
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
    	String[] quanArray = request.getParameterValues("quan");
    	System.out.println(quanArray[0]);
    	
    	String[] stocks_names = name_list[0].split(",");
    	
    	// purchase 만들기(임시로 무조건 user가 작성햇다고 판단)
    	SiteUser user2 = userservice.findUserByName2("user");
    	// 주문서 만들기
    	purchase purchase_t = this.purchaseService.create(user2);
    	
    	//일괄업로드위해 만든 리스트
    	List<purchasedetails> uploadList = new ArrayList<>(); 
    	
    	float total_price = 0;
    	
    	for(int i=0;i<quanArray.length;i++) {

    		purchasedetails tmp_details = new purchasedetails();
    		stocks stock = stocksService.findByName(stocks_names[i]);
    		
    		Float count = Float.parseFloat(quanArray[i]);
    		Float price = Float.parseFloat(stock.getPrice().replace("$", ""));
    		
    		tmp_details.setStock(stock);
    		tmp_details.setPurchase(purchase_t);
    		tmp_details.setCount(Integer.parseInt(quanArray[i]));
    		tmp_details.setPrice(Float.toString(price*count));
    		
    		total_price = total_price + price*count;
    		
    		uploadList.add(tmp_details);

    	}
    	this.purchaseService.updatePrice(purchase_t, total_price);
    	this.purchasedetailService.saveall(uploadList);

    	return "redirect:/copperbrass/mypage";
    }
    
    
    
    @GetMapping("/copperbrass/mypage")
    public String MyPage(Model model, Principal principal) {
    	

    	System.out.println("DDDDDHHHHHH"); //여기가문제 여기 고치기
    	SiteUser user = new SiteUser();
    	user = userservice.findUserByName2(principal.getName());
    	// user 비었을 때 예외처리 해줘야함
    	if(user.getUsername().isEmpty()) {
    		return "redirect:/copperbrass/shop";
    	}
    	
    	List<purchase> mypageList = purchaseService.findDeposit1status(user);
    	
    	//AuthRole(principal,model);
    	System.out.println("yyy");
    	model.addAttribute("mypageList",mypageList);
    	System.out.println(mypageList);
    	


    	model.addAttribute("test","test");
    	
    	return "mypage";
    }    
	
    
    @PostMapping("/copperbrass/mypage_details")
    public String MyPage_details(Model model,HttpServletRequest request) {
    	System.out.println("여기는들어옴");

    	String src = request.getParameter("newInputId");
    //해당되는 목록 가져오기
    	System.out.println(src);
    	
    	purchase p = this.purchaseService.getPurchasedetails(src);
    	
    	model.addAttribute("p",p);
    	
    	return "mypage-details";
    }  
    
	public void AuthRole(Principal principal, Model model) {
		if(principal != null) {
			model.addAttribute("principal",principal);
		}	
	}    
	

}
