package com.napgroup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.AskOrders;
import com.napgroup.repositories.AskOrdersRepository;

@Service
public class AskOrdersServiceImpl implements AskOrdersService {
	
	@Autowired
	private AskOrdersRepository askOrdersRepository;

	@Override
	public List<AskOrders> findUserAskOrders(int accountId) {
		return askOrdersRepository.findUserAskOrders(accountId);
	}
	
	@Override
	public Optional<AskOrders> findOrder(int orderId) {
		return askOrdersRepository.findById(orderId);
	}

}
