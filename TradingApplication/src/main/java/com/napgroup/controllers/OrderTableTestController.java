package com.napgroup.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderTableSuperComparator;
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

		List<OrderTableSuper> asks = askOrdersService.findCompleteOrdersByAccountId(1);	
		System.out.println(asks.size());
		Collections.sort(asks, new OrderTableSuperComparator());
		for(OrderTableSuper a: asks) {
			System.out.println("ORDER: " + a);
		}
		
		return "order_table";
	}
	
//	@GetMapping("/testBidOrders")
//	public List<OrderTable> findBidOrdersById(){
//		// List<OrderTable> orderTable = orderService.findBidOrdersById(4);
//		System.out.println(orderTable);
//		return orderTable;
//	}
}
