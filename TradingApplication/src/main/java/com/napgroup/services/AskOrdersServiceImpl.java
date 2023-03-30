package com.napgroup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.AskOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.Region;
import com.napgroup.repositories.AskOrdersRepository;

@Service
public class AskOrdersServiceImpl implements AskOrdersService {
	
	@Autowired
	private AskOrdersRepository askOrdersRepository;

	@Override
	public List<AskOrders> findUserAskOrdersByAccountId(int accountId) {
		return askOrdersRepository.findUserAskOrdersByAccountId(accountId);
	}

	@Override
	public List<AskOrders> findIncompleteOrdersByCompanyAndRegion(int companyId, Region region) {
		return askOrdersRepository.findIncompleteOrdersByCompanyAndRegion(companyId, region);
	}

	@Override
	public List<AskOrders> findCompleteOrdersByAccountId(int accountId) {
		return askOrdersRepository.findCompleteOrdersByAccountId(accountId);
	}
	
	@Override
	public List<AskOrders> findCompleteOrdersByAccountIdAndCompanyAndRegion(int accountId, int companyId, Region region) {
		return askOrdersRepository.findCompleteOrdersByAccountIdAndCompanyAndRegion(accountId, companyId, region);
	}
	
	@Override
	public AskOrders addAskOrder(AskOrders askOrder) {
		return askOrdersRepository.save(askOrder);
	}

	@Override
	public AskOrders updateOrderStatus(int orderId, OrderStatus orderStatus) {
		return askOrdersRepository.updateOrderStatus(orderId, orderStatus);
	}

	@Override
	public AskOrders updateStockAmount(int orderId, int stockAmount) {
		return askOrdersRepository.updateStockAmount(orderId, stockAmount);
	}

	@Override
	public AskOrders updateStockPrice(int orderId, double salePrice) {
		return askOrdersRepository.updateStockPrice(orderId, salePrice);
	}

}
