package com.napgroup.services;

import java.util.Optional;

public interface SortService {
	
	public Optional<Order> findAsk(Order order);
	public Optional<Order> findBid(Order order);
	public int executeTrade(Order ask, Order bid);

}
