package com.napgroup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.BidOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;
import com.napgroup.repositories.BidOrdersRepository;

@Service
public class BidOrdersServiceImpl implements BidOrderService {
	
	@Autowired
	private BidOrdersRepository bidOrdersRepository;

	@Override
	public List<OrderTableSuper> findUserOrdersByAccountId(int accountId) {
		return bidOrdersRepository.findUserBidOrdersByAccountId(accountId);
	}

	@Override
	public List<OrderTableSuper> findIncompleteOrdersByCompanyAndRegion(int companyId, Region region) {
		return bidOrdersRepository.findIncompleteOrdersByCompanyAndRegion(companyId, region);
	}

	@Override
	public List<OrderTableSuper> findCompleteOrdersByAccountId(int accountId) {
		return bidOrdersRepository.findCompleteOrdersByAccountId(accountId);
	}
	
	@Override
	public List<OrderTableSuper> findCompleteOrdersByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region) {
		return bidOrdersRepository.findCompleteOrdersByAccountIdAndCompanyAndRegion(accountId, companyId, region);
	}
	
	
	@Override
	public int findTotalStocksByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region) {
		List<OrderTableSuper> bids = bidOrdersRepository.findCompleteOrdersByAccountIdAndCompanyAndRegion(accountId, companyId, region);
		int total = 0;
		for(OrderTableSuper bid: bids) {
			total += bid.getStockAmount();
		}
		
		return total;
	}

	@Override
	public OrderTableSuper addOrder(OrderTableSuper order) {
		BidOrders bidOrder = (BidOrders) order;
		return bidOrdersRepository.save(bidOrder);
	}

	@Override
	public OrderTableSuper updateOrderStatus(int orderId, OrderStatus orderStatus) {
		return bidOrdersRepository.updateOrderStatus(orderId, orderStatus);
	}

	@Override
	public OrderTableSuper updateStockAmount(int orderId, int stockAmount) {
		return bidOrdersRepository.updateStockAmount(orderId, stockAmount);
	}

	@Override
	public OrderTableSuper updateStockPrice(int orderId, double salePrice) {
		return bidOrdersRepository.updateStockPrice(orderId, salePrice);
	}

}
