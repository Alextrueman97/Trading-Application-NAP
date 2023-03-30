package com.napgroup.services;

import java.util.Optional;

import com.napgroup.models.OrderTable;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;

public interface SortService {
	
	public Optional<OrderTableSuper> findAsk(OrderTableSuper order, Region region);
	public Optional<OrderTableSuper> findBid(OrderTableSuper order, Region region);
	public int executeTrade(OrderTableSuper ask, OrderTableSuper bid);

}
