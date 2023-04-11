package com.napgroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.napgroup.models.OrderTableSuper;
import com.napgroup.services.AskOrdersServiceImpl;
import com.napgroup.services.BidOrdersServiceImpl;
import com.napgroup.services.OrderTableSuperServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyHistoryController {

	@Autowired
	private OrderTableSuperServiceImpl orderTableSuperServiceImpl;
	@Autowired
	private AskOrdersServiceImpl askOrdersServiceImpl;
	@Autowired
	private BidOrdersServiceImpl bidOrdersServiceImpl;
	

	
	@GetMapping("/history")
	public String tradeHistory(HttpSession session, Model model) {
		int accountId = (int) session.getAttribute("accountId");
		List<OrderTableSuper> trades = orderTableSuperServiceImpl.findUserOrdersByAccountId(accountId);
		model.addAttribute("trades", trades);
		return "history";
	}
	
}
