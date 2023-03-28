package com.napgroup.models;

import java.util.List;

public class OrderBook {
	
	private List<OrderTable> orders;

	public OrderBook(List<OrderTable> orders) {
		super();
		this.orders = orders;
	}

	public List<OrderTable> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderTable> orders) {
		this.orders = orders;
	}

}
