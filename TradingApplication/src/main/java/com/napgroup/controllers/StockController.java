package com.napgroup.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.napgroup.models.Company;
import com.napgroup.models.MyUserDetails;
import com.napgroup.models.OrderBook;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Sort;
import com.napgroup.models.Stocks;
import com.napgroup.models.UserAccount;
import com.napgroup.models.UserStock;
import com.napgroup.services.CompanyService;
import com.napgroup.services.OrderTableSuperService;
import com.napgroup.services.SortServiceImpl;
import com.napgroup.services.StockService;
import com.napgroup.services.UserAccountService;

import jakarta.persistence.criteria.Order;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	public String buyStocks(@RequestParam("company") String companyName, @RequestParam("region") Region region,
			@RequestParam("stock-amount") int stockAmount, @RequestParam("price") double price,
			@RequestParam("order-type") String orderType, @AuthenticationPrincipal MyUserDetails user, Model model) {

		OrderType oType = OrderType.valueOf(orderType);
		Company company = companyService.findCompanyByCompanyName(companyName);
		Stocks stock = stockService.findStockByCompanyAndRegion(company, region);
		// UserAccount user = (UserAccount) request.getSession().getAttribute("loggedInUser");
		if(user.getBalance() >= (stockAmount * price)) {
			UserAccount userEntity = new UserAccount();
			BeanUtils.copyProperties(user, userEntity);
			OrderTableSuper order = new OrderTableSuper(userEntity, stock, price, stockAmount, OrderStatus.PENDING, oType,
					SaleType.BID, LocalDateTime.now());
			Map<Region, OrderBook> orderBooks = new HashMap<>();
			Sort sort = new Sort(orderBooks, company);
			sortServiceImpl.setSort(sort);
			sortServiceImpl.updateSort();
			sortServiceImpl.executeMatchAndTrade(order, region);
		} else {
			model.addAttribute("error", "Insufficient funds in your account to complete this transaction");
			return viewBuy(model);
		}
		return "redirect:/UserStocks";

	}
	
	@GetMapping("/Buy/{company}/{region}/{amount}")
	public String viewBuyCompanyStocks(@PathVariable("company") String company, @PathVariable("region") Region region,
			@PathVariable("amount") int amount, Model model) {
		List<Company> companies = List.of(companyService.findCompanyByCompanyName(company));
		Stocks stock = stockService.findStockByCompanyAndRegion(companies.get(0), region);
		List<Region> regions = List.of(region);
		model.addAttribute("companies", companies);
		model.addAttribute("regions", regions);
		model.addAttribute("setPrice", stock.getStockPrice());
		return "Buy";
	}
	
	@PostMapping("/Buy/{company}/{region}/{amount}")
	public String buyStocks(@PathVariable("amount") int amount, @PathVariable("company") String companyName, @PathVariable("region") Region region,
			@RequestParam("stock-amount") int stockAmount,
			@RequestParam("order-type") String orderType, Model model, @AuthenticationPrincipal MyUserDetails user) {
		
		// UserAccount user = (UserAccount) request.getSession().getAttribute("loggedInUser");
		// UserAccount user = userAccountService.login("nk@gmail.com", "pass");
		Company company = companyService.findCompanyByCompanyName(companyName);
		Stocks stock = stockService.findStockByCompanyAndRegion(company, region);
		double price = stock.getStockPrice();
		if(stockAmount < amount) {
			if(user.getBalance() >= (stockAmount * price)) {
				// OrderType oType = OrderType.valueOf(orderType);
				System.err.println(user.getAccountId());
				UserAccount userEntity = new UserAccount();
				BeanUtils.copyProperties(user, userEntity);
				OrderTableSuper order = new OrderTableSuper(userEntity, stock, price, stockAmount, OrderStatus.COMPLETE, OrderType.MARKET,
						SaleType.BID, LocalDateTime.now());
				orderTableSuperService.addOrder(order);
				stockService.updateStockAmountById(stock.getStockId(), amount - stockAmount);
				userAccountService.updateUserBalance(user.getBalance() - (stockAmount * price), user.getAccountId());
				// user.setBalance(user.getBalance() - (stockAmount * price));
			} else {
				
				model.addAttribute("error", "Insufficient funds in your account to complete this transaction");
				return viewBuyCompanyStocks(companyName, region, stockAmount, model);
			}
			
		} else {
			model.addAttribute("error", "There are not enough stocks available, please select a smaller amount");
			return viewBuyCompanyStocks(companyName, region, stockAmount, model);
		}
		
		return "redirect:/UserStocks";
	}

	@GetMapping("/History")
	public String viewHistory(Model model, @AuthenticationPrincipal MyUserDetails user) {
		// UserAccount user = (UserAccount) session.getAttribute("loggedInUser");
		List<OrderTableSuper> orders = orderTableSuperService.findUserOrdersByAccountId(user.getAccountId());
		model.addAttribute("orders", orders);
		return "History";
	}

	@GetMapping("/UserStocks")
	public String showMyStocks(Model model, @AuthenticationPrincipal MyUserDetails user) {
		// UserAccount user = (UserAccount) session.getAttribute("loggedInUser");
		List<Company> companies = companyService.findAllCompanies();
		List<UserStock> stocks = new LinkedList<>();
		for (Company company : companies) {
			stocks.add(new UserStock(company, Region.LSE,
					orderTableSuperService.findTotalStocksByAccountIdAndCompanyAndRegion(user.getAccountId(),
							company.getCompanyId(), Region.LSE)));
			stocks.add(new UserStock(company, Region.SSE,
					orderTableSuperService.findTotalStocksByAccountIdAndCompanyAndRegion(user.getAccountId(),
							company.getCompanyId(), Region.SSE)));
			stocks.add(new UserStock(company, Region.NYSE,
					orderTableSuperService.findTotalStocksByAccountIdAndCompanyAndRegion(user.getAccountId(),
							company.getCompanyId(), Region.NYSE)));

		}

		model.addAttribute("stocks", stocks);
		return "UserStocks";

	}

	@GetMapping("/Sell/{company}/{region}/{amount}")
	public String viewSell(@PathVariable("company") String company, @PathVariable("region") Region region,
			@PathVariable("amount") int amount, Model model) {

		model.addAttribute("company", company);
		model.addAttribute("region", region);
		model.addAttribute("limit", amount);

		return "Sell";
	}

	@PostMapping("/Sell/{company}/{region}/{amount}")
	public String viewSell(@PathVariable("company") String companyName, @PathVariable("region") Region region,
			@PathVariable("amount") int amount, @RequestParam("stock-amount") int stockAmount,
			@RequestParam("price") double price, @RequestParam("order-type") String orderType, @AuthenticationPrincipal MyUserDetails user) {

		OrderType oType = OrderType.valueOf(orderType);
		Company company = companyService.findCompanyByCompanyName(companyName);
		Stocks stock = stockService.findStockByCompanyAndRegion(company, region);
		System.err.println(stock.getStockId());
		// UserAccount user = (UserAccount) session.getAttribute("loggedInUser");
		System.err.println(user.getAccountId());
		if (stockAmount <= amount) {
			UserAccount userEntity = new UserAccount();
			BeanUtils.copyProperties(user, userEntity);
			OrderTableSuper order = new OrderTableSuper(userEntity, stock, price, stockAmount, OrderStatus.PENDING, oType,
					SaleType.ASK, LocalDateTime.now());
			Map<Region, OrderBook> orderBooks = new HashMap<>();
			Sort sort = new Sort(orderBooks, company);
			sortServiceImpl.setSort(sort);
			sortServiceImpl.updateSort();
			sortServiceImpl.executeMatchAndTrade(order, region);
			return "redirect:/Account";

		}

		return "UserStocks";
	}

}
