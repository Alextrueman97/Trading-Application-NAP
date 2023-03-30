package com.napgroup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.AskOrders;
import com.napgroup.models.OrderStatus;
import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;

@Service
public class OrderTableSuperServiceImpl implements OrderTableSuperService {
	
	@Autowired
	private AskOrdersService askOrdersService;

	@Override
	public List<OrderTableSuper> findUserOrdersByAccountId(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderTableSuper> findIncompleteOrdersByCompanyAndRegion(int companyId, Region region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderTableSuper> findCompleteOrdersByAccountId(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderTableSuper> findTotalStocksByAccountIdAndCompanyAndRegion(int accountId, int companyId,
			Region region) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTableSuper addAskOrder(OrderTableSuper order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTableSuper updateOrderStatus(int orderId, OrderStatus orderStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTableSuper updateStockAmount(int orderId, int stockAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTableSuper updateStockPrice(int orderId, double salePrice) {
		// TODO Auto-generated method stub
		return null;
	}



}
