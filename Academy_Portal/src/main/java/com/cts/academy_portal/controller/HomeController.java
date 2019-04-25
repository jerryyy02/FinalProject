package com.cts.academy_portal.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/*This is the first mapping that will be called when the application is first up
	And it will land the user on the login page*/
	@RequestMapping("/")
	public String getHomePage(){
		return "login";
	}
	
}
