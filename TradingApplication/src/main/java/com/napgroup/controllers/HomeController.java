package com.napgroup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@GetMapping("/index")
	public String ind(HttpServletRequest request) {
		return "index";
	}
	
}
