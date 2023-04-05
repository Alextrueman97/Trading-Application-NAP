package com.napgroup.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class BidOrders extends OrderTableSuper {

	public BidOrders() {
		super();
	}

	public BidOrders(int orderId, Stocks stockId, double salePrice, int stockAmount, OrderStatus orderStatus,
			OrderType orderType, SaleType saleType, LocalDateTime saleDate) {
		super(orderId, stockId, salePrice, stockAmount, orderStatus, orderType, saleType, saleDate);
		// TODO Auto-generated constructor stub
	}
	
	public BidOrders(int orderId, UserAccount accountId, Stocks stockId, double salePrice, int stockAmount,
			OrderStatus orderStatus, OrderType orderType, SaleType saleType, LocalDateTime saleDate) {
		super(orderId, accountId, stockId, salePrice, stockAmount, orderStatus, orderType, saleType, saleDate);
		// TODO Auto-generated constructor stub
	}
	
	public BidOrders(UserAccount accountId, Stocks stockId, double salePrice, int stockAmount,
			OrderStatus orderStatus, OrderType orderType, SaleType saleType, LocalDateTime saleDate) {
		super(accountId, stockId, salePrice, stockAmount, orderStatus, orderType, saleType, saleDate);
		// TODO Auto-generated constructor stub
	}
	
	public BidOrders(Stocks stockId, double salePrice, int stockAmount, OrderStatus orderStatus, OrderType orderType,
			SaleType saleType, LocalDateTime saleDate) {
		super(stockId, salePrice, stockAmount, orderStatus, orderType, saleType, saleDate);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
