package com.napgroup.models;

public class OrderBook {
	
	private List<Order> orders;

	public OrderBook(List<Order> orders) {
		super();
		this.orders = orders;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
