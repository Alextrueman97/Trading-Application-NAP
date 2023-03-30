package com.napgroup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.napgroup.models.BidOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.Region;
import com.napgroup.repositories.BidOrdersRepository;

public class BidOrdersServiceImpl implements BidOrderService {
	
	@Autowired
	private BidOrdersRepository bidOrdersRepository;

	@Override
	public List<BidOrders> findUserBidOrdersByAccountId(int accountId) {
		return bidOrdersRepository.findUserBidOrdersByAccountId(accountId);
	}

	@Override
	public List<BidOrders> findIncompleteOrdersByCompanyAndRegion(int companyId, Region region) {
		return bidOrdersRepository.findIncompleteOrdersByCompanyAndRegion(companyId, region);
	}

	@Override
	public List<BidOrders> findCompleteOrdersByAccountId(int accountId) {
		return bidOrdersRepository.findCompleteOrdersByAccountId(accountId);
	}

	@Override
	public BidOrders addBidOrder(BidOrders bidOrder) {
		return bidOrdersRepository.save(bidOrder);
	}

	@Override
	public BidOrders updateOrderStatus(int orderId, OrderStatus orderStatus) {
		return bidOrdersRepository.updateOrderStatus(orderId, orderStatus);
	}

	@Override
	public BidOrders updateStockAmount(int orderId, int stockAmount) {
		return bidOrdersRepository.updateStockAmount(orderId, stockAmount);
	}

	@Override
	public BidOrders updateStockPrice(int orderId, double salePrice) {
		return bidOrdersRepository.updateStockPrice(orderId, salePrice);
	}

}
