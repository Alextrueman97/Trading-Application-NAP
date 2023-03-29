//package com.napgroup.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.napgroup.models.OrderStatus;
//import com.napgroup.models.OrderTable;
//import com.napgroup.repositories.OrderTableRepository;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//	@Autowired OrderTableRepository orderTableRepository;
//	
//	//@Override
//	//public List<OrderTable> findUsersOrders(int accountId){
//		//return orderTableRepository.findAllOrdersByUserId(accountId);
//	//}
//	
//	@Override
//	public List<OrderTable> findAskOrdersById(int accountId){
//		return orderTableRepository.findAskOrdersById(accountId);
//	}
//	
//	@Override
//	public List<OrderTable> findBidOrdersById(int accountId){
//		return orderTableRepository.findBidOrdersById(accountId);
//	}
//	
//	@Override
//	public List<OrderTable> findIncompleteOrders(int companyId, String region){
//		return orderTableRepository.findAllUnfilledOrdersByCompanyAndRegion(companyId, region);
//	}
//	@Override
//	public List<OrderTable> findCompleteOrders(int accountId){
//		return orderTableRepository.findAllFilledOrdersByUserId(accountId);
//	}
//	@Override
//	public OrderTable addOrder(OrderTable order) {
//		return orderTableRepository.save(order);
//	}
//	@Override
//	public OrderTable updateOrderStatus(int orderId, OrderStatus orderStatus) {
//		return orderTableRepository.updateOrderStatusById(orderId, orderStatus);
//	}
//	@Override
//	public OrderTable updateStockAmount(int orderId, int stockAmount) {
//		return orderTableRepository.updateStockAmountById(orderId, stockAmount);
//	}
//	@Override
//	public OrderTable updateStockPrice(int orderId, double stockPrice) {
//		return orderTableRepository.updateStockPriceById(orderId, stockPrice);
//	}
//}
