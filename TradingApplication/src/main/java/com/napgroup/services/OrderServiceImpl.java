package com.napgroup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.napgroup.models.OrderTable;
import com.napgroup.repositories.OrderTableRepository;

public class OrderServiceImpl implements OrderService {

	@Autowired OrderTableRepository orderTableRepository;
	
	public List<OrderTable> findUsersOrders(int accountId){
		return orderTableRepository.findAllOrdersByUserId(accountId);
	}
	
	public List<OrderTable> findIncompleteOrders(int companyId, String region){
		return orderTableRepository.findAllUnfilledOrdersByCompanyAndRegion(companyId, region);
	}
	
	public List<OrderTable> findCompleteOrders(int accountId){
		return orderTableRepository.findAllFilledOrdersByUserId(accountId);
	}
}
