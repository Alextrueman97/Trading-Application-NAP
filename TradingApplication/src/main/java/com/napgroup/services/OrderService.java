package com.napgroup.services;

import java.util.List;

import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTable;

public interface OrderService {
	
	public List<OrderTable> findAllOrdersByUserId(int userId);
	public List<OrderTable> findAllUnfilledOrdersByCompanyAndRegion(int companyId, String region);
	public List<OrderTable> findAllFilledOrdersByUserId(int userId);
	public OrderTable addOrder(OrderTable order);
	public OrderTable updateOrderStatusById(int orderId, OrderStatus orderStatus);
	public OrderTable updateStockAmountById(int orderId, int stockAmount);
	public OrderTable updateStockPriceById(int orderId, double stockPrice);
	
}
