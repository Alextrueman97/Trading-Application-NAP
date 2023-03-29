package com.napgroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.napgroup.models.OrderTable;
import com.napgroup.services.OrderService;

@Controller
public class OrderTableTestController {

	@Autowired 
	private OrderService orderService;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/testAskOrders")
	public String findAskOrdersById(){
	//	List<OrderTable> orderTable = orderService.findAskOrdersById(3);
	//	System.out.println(orderTable);
		return "order_table";
	}
	
	@GetMapping("/testBidOrders")
	public List<OrderTable> findBidOrdersById(){
		List<OrderTable> orderTable = orderService.findBidOrdersById(4);
		System.out.println(orderTable);
		return orderTable;
	}
}
