package com.napgroup.services;

import java.util.Optional;

import com.napgroup.models.OrderTable;

public interface SortService {
	
	public Optional<OrderTable> findAsk(OrderTable order);
	public Optional<OrderTable> findBid(OrderTable order);
	public int executeTrade(OrderTable ask, OrderTable bid);

}
