package com.napgroup.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.napgroup.models.Company;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;
import com.napgroup.models.UserAccount;
import com.napgroup.services.CompanyServiceImpl;
import com.napgroup.services.OrderTableSuperServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyStocksController {

	@Autowired
	private OrderTableSuperServiceImpl orderTableSuperServiceImpl;
	@Autowired
	private CompanyServiceImpl companyServiceImpl;

	@GetMapping("/MyStocks")
	public String showMyStocks(Model model, HttpSession session) {
		// UserAccount accountId = (UserAccount) session.getAttribute("loggedInUser");
		List<Company> companies = companyServiceImpl.findAllCompanies();
		Map<Company, Map<Region, Integer>> stocks = new HashMap<>();
//		for(Company company: companies) {
//			Map<Region, Integer> map = new HashMap<Region, Integer>();
//			map.put(Region.LSE, orderTableSuperServiceImpl.findTotalStocksByAccountIdAndCompanyAndRegion(accountId, company.getCompanyId(), Region.LSE));
//			map.put(Region.SSE, orderTableSuperServiceImpl.findTotalStocksByAccountIdAndCompanyAndRegion(accountId, company.getCompanyId(), Region.SSE));
//			map.put(Region.NYSE, orderTableSuperServiceImpl.findTotalStocksByAccountIdAndCompanyAndRegion(accountId, company.getCompanyId(), Region.NYSE));
//			stocks.put(company, map);
//		}

		for (Company company : companies) {
			Map<Region, Integer> map = new HashMap<Region, Integer>();
			map.put(Region.LSE, 10);
			map.put(Region.SSE, 11);
			map.put(Region.NYSE, 12);
			stocks.put(company, map);
		}

		model.addAttribute("stocks", stocks);
		return "MyStocks";

	}
}
