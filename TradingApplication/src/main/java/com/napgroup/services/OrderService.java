package com.napgroup.services;

import java.util.List;

import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTable;

public interface OrderService {
	
	//public List<OrderTable> findUsersOrders(int accountId);
	public List<OrderTable> findAskOrdersById(int accountId);
	public List<OrderTable> findBidOrdersById(int accountId);
	public List<OrderTable> findIncompleteOrders(int companyId, String region);
	public List<OrderTable> findCompleteOrders(int userId);
	public OrderTable addOrder(OrderTable order);
	public OrderTable updateOrderStatus(int orderId, OrderStatus orderStatus);
	public OrderTable updateStockAmount(int orderId, int stockAmount);
	public OrderTable updateStockPrice(int orderId, double stockPrice);
	
}
