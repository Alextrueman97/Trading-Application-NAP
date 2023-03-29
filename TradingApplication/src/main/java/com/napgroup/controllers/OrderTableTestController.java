package com.napgroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.napgroup.models.AskOrders;
import com.napgroup.models.OrderTable;
import com.napgroup.services.AskOrdersService;

@Controller
public class OrderTableTestController {

	@Autowired 
	private AskOrdersService askOrdersService;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/testAskOrders")
	public String findAskOrdersById(){

		List<AskOrders> askOrders = askOrdersService.findUserAskOrders(1);
		System.out.println(askOrders.get(0));
//		AskOrders ask = askOrdersService.findOrder(1).get();
//		System.out.println(ask);
		return "order_table";
	}
	
	@GetMapping("/testBidOrders")
	public List<OrderTable> findBidOrdersById(){
		// List<OrderTable> orderTable = orderService.findBidOrdersById(4);
		System.out.println(orderTable);
		return orderTable;
	}
}
