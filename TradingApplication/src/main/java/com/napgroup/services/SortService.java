package com.napgroup.services;

import java.util.Optional;

import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;

public interface SortService {
	
	public Optional<OrderTableSuper> findAsk(OrderTableSuper order, Region region);
	public Optional<OrderTableSuper> findBid(OrderTableSuper order, Region region);
	public Optional<OrderTableSuper> findMatch(OrderTableSuper order, Region region);
	public Optional<OrderTableSuper> executeBidTrade(OrderTableSuper bid, OrderTableSuper ask)
	public Optional<OrderTableSuper> executeAskTrade(OrderTableSuper ask, OrderTableSuper ask)
	public Optional<OrderTableSuper> executeTrade(OrderTableSuper ask, OrderTableSuper bid);
	public void executeMatchAndTrade(OrderTableSuper order, Region region);

}
