package com.napgroup.services;

import java.util.List;

import com.napgroup.models.AskOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;

public interface AskOrdersService extends OrderTableSuperService {
	
	public List<OrderTableSuper> findCompleteOrdersByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region);
	public OrderTableSuper updateOrderStatus(int orderId, OrderStatus orderStatus);
	public OrderTableSuper updateStockAmount(int orderId, int stockAmount);
	public OrderTableSuper updateStockPrice(int orderId, double salePrice);

}
