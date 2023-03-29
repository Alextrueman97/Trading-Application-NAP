package com.napgroup.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class AskOrders extends OrderTableSuper {
	
	public AskOrders() {super();}
	

	public AskOrders(int orderId, UserAccount accountId, Stocks stockId, double salePrice, int stockAmount,
			OrderStatus orderStatus, OrderType orderType, SaleType saleType, LocalDateTime saleDate) {
		super(orderId, accountId, stockId, salePrice, stockAmount,
				orderStatus, orderType, saleType,saleDate);
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
