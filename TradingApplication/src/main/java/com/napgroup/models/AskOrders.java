package com.napgroup.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class AskOrders extends OrderTableSuper {
	
	public AskOrders() {
		super();
	}


	public AskOrders(int orderId, Stocks stockId, double salePrice, int stockAmount, OrderStatus orderStatus,
			OrderType orderType, SaleType saleType, LocalDateTime saleDate) {
		super(orderId, stockId, salePrice, stockAmount, orderStatus, orderType, saleType, saleDate);
		// TODO Auto-generated constructor stub
	}



	public AskOrders(int orderId, UserAccount accountId, Stocks stockId, double salePrice, int stockAmount,
			OrderStatus orderStatus, OrderType orderType, SaleType saleType, LocalDateTime saleDate) {
		super(orderId, accountId, stockId, salePrice, stockAmount, orderStatus, orderType, saleType, saleDate);
		// TODO Auto-generated constructor stub
	}



	public AskOrders(Stocks stockId, double salePrice, int stockAmount, OrderStatus orderStatus, OrderType orderType,
			SaleType saleType, LocalDateTime saleDate) {
		super(stockId, salePrice, stockAmount, orderStatus, orderType, saleType, saleDate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
