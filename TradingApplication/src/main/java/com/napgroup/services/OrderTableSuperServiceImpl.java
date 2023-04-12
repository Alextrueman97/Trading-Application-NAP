package com.napgroup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;
import com.napgroup.models.SaleType;

@Service
public class OrderTableSuperServiceImpl implements OrderTableSuperService {
	
	@Autowired
	private AskOrdersService askOrdersService;
	@Autowired
	private BidOrderService bidOrderService;

	@Override
	public List<OrderTableSuper> findUserOrdersByAccountId(int accountId) {
		List<OrderTableSuper> orders = askOrdersService.findUserOrdersByAccountId(accountId);
		orders.addAll(bidOrderService.findUserOrdersByAccountId(accountId));
		return orders;
	}

	@Override
	public List<OrderTableSuper> findIncompleteOrdersByCompanyAndRegion(int companyId, Region region) {
		List<OrderTableSuper> orders = askOrdersService.findIncompleteOrdersByCompanyAndRegion(companyId, region);
		orders.addAll(bidOrderService.findIncompleteOrdersByCompanyAndRegion(companyId, region));
		return orders;
	}

	@Override
	public List<OrderTableSuper> findCompleteOrdersByAccountId(int accountId) {
		List<OrderTableSuper> orders = askOrdersService.findCompleteOrdersByAccountId(accountId);
		orders.addAll(bidOrderService.findCompleteOrdersByAccountId(accountId));
		return orders;
	}

	@Override
	public int findTotalStocksByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region) {
		
		int totalAskStocks = askOrdersService.findTotalStocksByAccountIdAndCompanyAndRegion(accountId, companyId, region);
		int totalBidStocks = bidOrderService.findTotalStocksByAccountIdAndCompanyAndRegion(accountId, companyId, region);
		int totalStocks = totalBidStocks - totalAskStocks;
		
		return totalStocks;
		
	}

	@Override
	public OrderTableSuper addOrder(OrderTableSuper order) {
		if(order.getSaleType() == SaleType.ASK) {
			askOrdersService.addOrder(order);
		} else if(order.getSaleType() == SaleType.BID) {
			bidOrderService.addOrder(order);
		}
		return order;
	}

	public OrderTableSuperServiceImpl(AskOrdersServiceImpl askOrdersService, BidOrdersServiceImpl bidOrderService) {
		super();
		this.askOrdersService = askOrdersService;
		this.bidOrderService = bidOrderService;
	}	

	@Override
	public int deleteOrder(OrderTableSuper order) {
		int delete = 0;
		if(order.getSaleType()== SaleType.ASK) {
			delete = askOrdersService.deleteOrder(order);
		}else if(order.getSaleType() == SaleType.BID) {
			delete= bidOrderService.deleteOrder(order);
		}
		return delete;
	}

}
