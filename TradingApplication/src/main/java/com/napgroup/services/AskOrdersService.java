package com.napgroup.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.napgroup.models.AskOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderType;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;
import com.napgroup.models.Stocks;

import jakarta.transaction.Transactional;

public interface AskOrdersService {
	
	public List<AskOrders> findUserAskOrdersByAccountId( int accountId);
	public List<AskOrders> findIncompleteOrdersByCompanyAndRegion(int companyId, Region region);
	public List<AskOrders> findCompleteOrdersByAccountId(int accountId);
	public List<AskOrders> findCompleteOrdersByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region);
	public AskOrders addAskOrder(AskOrders askOrder);
	public AskOrders updateOrderStatus(int orderId, OrderStatus orderStatus);
	public AskOrders updateStockAmount(int orderId, int stockAmount);
	public AskOrders updateStockPrice(int orderId, double salePrice);

}
