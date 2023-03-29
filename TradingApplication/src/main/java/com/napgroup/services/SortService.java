package com.napgroup.services;

import java.util.Optional;

import com.napgroup.models.OrderTable;
import com.napgroup.models.Region;

public interface SortService {
	
	public Optional<OrderTable> findAsk(OrderTable order, Region region);
	public Optional<OrderTable> findBid(OrderTable order, Region region);
	public int executeTrade(OrderTable ask, OrderTable bid);

}
