package com.napgroup.models;

import java.util.List;

public class OrderBook {
	
	private List<OrderTableSuper> orders;

	public OrderBook() {}
	
	public OrderBook(List<OrderTableSuper> orders) {
		super();
		this.orders = orders;
	}

	public List<OrderTableSuper> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderTableSuper> orders) {
		this.orders = orders;
	}

}
