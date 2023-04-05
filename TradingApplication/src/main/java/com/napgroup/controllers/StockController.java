package com.napgroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.napgroup.services.StockServiceImpl;

@Controller
public class StockController {
	
	@Autowired
	private StockServiceImpl stockServiceImpl;
	
	@GetMapping("/Dashboard")
	public String showAllStocks(Model model) {
		List<Object[]> stocks = stockServiceImpl.findAllStocksWithCompanyInfo();
		model.addAttribute("stocks", stocks);
		System.out.println(stocks);
		return "/Dashboard";
	}
}
