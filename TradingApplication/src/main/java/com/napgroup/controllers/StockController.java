package com.napgroup.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.napgroup.models.Company;
import com.napgroup.models.OrderBook;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;
import com.napgroup.models.Stocks;
import com.napgroup.models.UserAccount;
import com.napgroup.services.CompanyService;
import com.napgroup.services.OrderTableSuperService;
import com.napgroup.services.SortServiceImpl;
import com.napgroup.services.StockService;
import com.napgroup.services.UserAccountService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class StockController {
	
	@Autowired
	private StockService stockService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	@Qualifier("orderTableSuperServiceImpl")
	private OrderTableSuperService orderTableSuperService;
	@Autowired
	private SortServiceImpl sortServiceImpl;
	@Autowired
	private UserAccountService userAccountService;
	
	@GetMapping("/Dashboard")
	public String showAllStocks(Model model) {
		List<Object[]> stocks = stockService.findAllStocksWithCompanyInfo();
		model.addAttribute("stocks", stocks);
		System.out.println(stocks);
		return "/Dashboard";
	}
	
	@GetMapping("/Buy")
	public String viewBuy(Model model) {
		List<Company> companies = companyService.findAllCompanies();
		List<Region> regions = List.of(Region.LSE, Region.NYSE, Region.SSE);
		model.addAttribute("companies", companies);
		model.addAttribute("regions", regions);
		return "Buy";
	}
	
	@PostMapping("/Buy")
	public String buyStocks(@RequestParam("company") String companyName, @RequestParam("region") Region region, @RequestParam("stock-amount") int stockAmount, 
			@RequestParam("price") double price, @RequestParam("order-type") String orderType, HttpServletRequest request) {
		
		OrderType oType = OrderType.valueOf(orderType);
		Company company = companyService.findCompanyByCompanyName(companyName);
		Stocks stock = stockService.findStockByCompanyAndRegion(company, region);
		System.err.println(stock.getStockId());
		UserAccount user = userAccountService.login("nk@gmail.com", "pass");
				//(UserAccount) request.getSession().getAttribute("loggedInUser");
		System.err.println(user.getAccountId());
		OrderTableSuper order = orderTableSuperService.addOrder(new OrderTableSuper(user, stock, price, stockAmount, OrderStatus.PENDING, oType, SaleType.BID, LocalDateTime.now()));
		if(order != null) {
			Map<Region, OrderBook> orderBooks = new HashMap<>();
			Sort sort = new Sort(orderBooks, company);
			sortServiceImpl.setSort(sort);
			sortServiceImpl.updateSort();
			sortServiceImpl.executeMatchAndTrade(order, region);
			return "Account";
		}
		
		return "Buy";
		
	}
	
	@GetMapping("/ViewHistory")
	public List<OrderTableSuper> viewHistory(HttpServletRequest request) {
		UserAccount user = userAccountService.login("nk@gmail.com", "pass");
		//(UserAccount) request.getSession().getAttribute("loggedInUser");
		return orderTableSuperService.findUserOrdersByAccountId(user.getAccountId());
	}
	
}
