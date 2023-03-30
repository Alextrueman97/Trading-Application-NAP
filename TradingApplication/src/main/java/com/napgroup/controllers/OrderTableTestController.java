package com.napgroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.napgroup.models.AskOrders;
import com.napgroup.models.Region;
import com.napgroup.models.UserAccount;
import com.napgroup.services.AskOrdersService;
import com.napgroup.services.CompanyService;
import com.napgroup.services.StockService;
import com.napgroup.services.UserAccountService;

@Controller
public class OrderTableTestController {

	@Autowired 
	private AskOrdersService askOrdersService;
	@Autowired
	private StockService stockService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/")
	public String test() {
		return "index";
	}
	
	@GetMapping("/testAskOrders")
	public String findAskOrdersById(){

//		List<AskOrders> asks = askOrdersService.findCompleteOrdersByAccountIdAndCompanyAndRegion(1, 1, Region.SSE);	
//		System.out.println(asks.get(0));
//		System.out.println(asks.size());
		
		return "order_table";
	}
	
//	@GetMapping("/testBidOrders")
//	public List<OrderTable> findBidOrdersById(){
//		// List<OrderTable> orderTable = orderService.findBidOrdersById(4);
//		System.out.println(orderTable);
//		return orderTable;
//	}
}
