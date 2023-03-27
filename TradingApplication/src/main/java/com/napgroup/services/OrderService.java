package com.napgroup.services;

public interface OrderService {
	
	public List<Order> findAllOrdersByUserId(int userId);
	public List<Order> findAllUnfilledOrdersByCompanyAndRegion(int companyId, String region);
	public List<Order> findAllFilledOrdersByUserId(int userId);
	public Order addOrder(Order order);
	public Order updateOrderStatusById(int orderId, OrderStatus orderStatus);
	public Order updateStockAmountById(int orderId, OrderStatus orderStatus);
	public Order updateStockPriceById(int orderId, OrderStatus orderStatus);
	
}
