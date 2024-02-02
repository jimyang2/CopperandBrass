package com.copperbrass.practice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {

	@GetMapping("/copperbrass")
	public String hello() {
		
		return "main";
	}
	
	
}
