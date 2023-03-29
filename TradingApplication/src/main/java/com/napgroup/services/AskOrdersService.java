package com.napgroup.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.napgroup.models.AskOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderType;
import com.napgroup.models.SaleType;
import com.napgroup.models.Stocks;

public interface AskOrdersService {
	
	
	public List<AskOrders> findUserAskOrders(int accountId);
	public Optional<AskOrders> findOrder(int orderId);

}
