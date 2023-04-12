package com.napgroup.services;

import java.util.List;
import java.util.Optional;

import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;

public interface OrderTableSuperService {
	
	public List<OrderTableSuper> findUserOrdersByAccountId(int accountId);
	public List<OrderTableSuper> findIncompleteOrdersByCompanyAndRegion(int companyId, Region region);
	public List<OrderTableSuper> findCompleteOrdersByAccountId(int accountId);
	public int findTotalStocksByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region);
	public OrderTableSuper addOrder(OrderTableSuper order);
	public int deleteOrder(OrderTableSuper orderId);
	
}
