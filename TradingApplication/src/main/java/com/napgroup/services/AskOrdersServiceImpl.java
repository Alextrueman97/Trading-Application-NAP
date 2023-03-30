package com.napgroup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.AskOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;
import com.napgroup.repositories.AskOrdersRepository;

@Service
public class AskOrdersServiceImpl implements AskOrdersService {
	
	@Autowired
	private AskOrdersRepository askOrdersRepository;

	@Override
	public List<OrderTableSuper> findUserOrdersByAccountId(int accountId) {
		return askOrdersRepository.findUserAskOrdersByAccountId(accountId);
	}

	@Override
	public List<OrderTableSuper> findIncompleteOrdersByCompanyAndRegion(int companyId, Region region) {
		return askOrdersRepository.findIncompleteOrdersByCompanyAndRegion(companyId, region);
	}

	@Override
	public List<OrderTableSuper> findCompleteOrdersByAccountId(int accountId) {
		return askOrdersRepository.findCompleteOrdersByAccountId(accountId);
	}
	
	@Override
	public List<OrderTableSuper> findCompleteOrdersByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region) {
		return askOrdersRepository.findCompleteOrdersByAccountIdAndCompanyAndRegion(accountId, companyId, region);
	}
	
	@Override
	public int findTotalStocksByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region) {
		List<OrderTableSuper> asks = askOrdersRepository.findCompleteOrdersByAccountIdAndCompanyAndRegion(accountId, companyId, region);
		int total = 0;
		for(OrderTableSuper ask: asks) {
			total += ask.getStockAmount();
		}
		
		return total;
	}
	
	@Override
	public OrderTableSuper addOrder(OrderTableSuper order) {
		AskOrders askOrder = (AskOrders) order;
		return askOrdersRepository.save(askOrder);
	}

	@Override
	public OrderTableSuper updateOrderStatus(int orderId, OrderStatus orderStatus) {
		return askOrdersRepository.updateOrderStatus(orderId, orderStatus);
	}

	@Override
	public OrderTableSuper updateStockAmount(int orderId, int stockAmount) {
		return askOrdersRepository.updateStockAmount(orderId, stockAmount);
	}

	@Override
	public OrderTableSuper updateStockPrice(int orderId, double salePrice) {
		return askOrdersRepository.updateStockPrice(orderId, salePrice);
	}

}
