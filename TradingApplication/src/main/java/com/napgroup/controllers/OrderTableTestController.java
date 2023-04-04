package com.napgroup.controllers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.napgroup.models.Company;
import com.napgroup.models.OrderBook;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderTableSuperComparator;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;
import com.napgroup.models.Stocks;
import com.napgroup.models.UserAccount;
import com.napgroup.services.AskOrdersService;
import com.napgroup.services.BidOrderService;
import com.napgroup.services.CompanyService;
import com.napgroup.services.ExternalBankingService;
import com.napgroup.services.OrderTableSuperService;
import com.napgroup.services.SortServiceImpl;
import com.napgroup.services.StockService;
import com.napgroup.services.UserAccountService;

@Controller
public class OrderTableTestController {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private StockService stockService;
	@Autowired
	private CompanyService companyService;
	@Autowired 
	private AskOrdersService askOrdersService;
	@Autowired
	private BidOrderService bidOrderService;
	@Autowired
	@Qualifier("orderTableSuperServiceImpl")
	private OrderTableSuperService orderTableSuperService;
	@Autowired
	private SortServiceImpl sortServiceImpl;
	
	@Autowired
	private ExternalBankingService externalBankingService;
	
	@GetMapping("/testExternalBanking")
	public String test(Model model) {
		HttpStatus test = externalBankingService.login("07864700009", "pass", 1000002);
		model.addAttribute("status", test.toString());
		return "index";
	}
	
	@GetMapping("/testExternalBankingWithdraw")
	public String testWithdraw(Model model) {
		HttpStatus test = externalBankingService.withdraw("07864700009", "pass", 1000002, 10);
		model.addAttribute("status", test.toString());
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
		askOrdersService.addOrder(asks.get(0));
		
		return "order_table";
	}
	
	@GetMapping("/testExecuteTrade")
	public String executeTrade(){
		
		Map<Region, OrderBook> orderBooks = new HashMap<>();
		Company company = companyService.findCompanyById(1);
		Sort sort = new Sort(orderBooks, company);
		sortServiceImpl.setSort(sort);
		sortServiceImpl.updateSort();
		UserAccount user = userAccountService.findUserById(1);
		Stocks stocks = stockService.findStockById(2);
		OrderTableSuper order = new OrderTableSuper(user, stocks, 90, 60, OrderStatus.PENDING, OrderType.MARKET, SaleType.ASK, LocalDateTime.now());
		// OrderTableSuper order = new OrderTableSuper(user, stocks, 300, 50, OrderStatus.PENDING, OrderType.LIMIT, SaleType.BID, LocalDateTime.now());
		sortServiceImpl.executeMatchAndTrade(order, Region.SSE);
		
		return "order_table";
	}
	
//	@GetMapping("/testBidOrders")
//	public List<OrderTable> findBidOrdersById(){
//		// List<OrderTable> orderTable = orderService.findBidOrdersById(4);
//		System.out.println(orderTable);
//		return orderTable;
//	}
}
