package com.napgroup.services;

import java.util.List;
import java.util.Optional;

import com.napgroup.models.AskOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;

public interface AskOrdersService extends OrderTableSuperService {
	
	public OrderTableSuper findOrderById(int orderId);
	public List<OrderTableSuper> findCompleteOrdersByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region);
	public int updateOrderStatus(int orderId, OrderStatus orderStatus);
	public int updateStockAmount(int orderId, int stockAmount);
	public int updateStockPrice(int orderId, double salePrice);
	
}
