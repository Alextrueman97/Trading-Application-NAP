package com.napgroup.models;

import java.util.Map;

public class Sort {
	
	// REGION -> orderBook for that region
	private Map<Region, OrderBook> orderBooks;
	private Company company;
	
	public Sort() {
		super();
	}
	
	public Sort(Map<Region, OrderBook> orderBooks, Company company) {
		super();
		this.orderBooks = orderBooks;
		this.company = company;
	}

	public Map<Region, OrderBook> getOrderBooks() {
		return orderBooks;
	}

	public void setOrderBooks(Map<Region, OrderBook> orderBooks) {
		this.orderBooks = orderBooks;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
