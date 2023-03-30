package com.napgroup.services;

import java.util.List;

import com.napgroup.models.AskOrders;
import com.napgroup.models.BidOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.Region;

public interface BidOrderService {
	
	public List<BidOrders> findUserBidOrdersByAccountId( int accountId);
	public List<BidOrders> findIncompleteOrdersByCompanyAndRegion(int companyId, Region region);
	public List<BidOrders> findCompleteOrdersByAccountId(int accountId);
	public BidOrders addBidOrder(BidOrders BidOrder);
	public BidOrders updateOrderStatus(int orderId, OrderStatus orderStatus);
	public BidOrders updateStockAmount(int orderId, int stockAmount);
	public BidOrders updateStockPrice(int orderId, double salePrice);

}
