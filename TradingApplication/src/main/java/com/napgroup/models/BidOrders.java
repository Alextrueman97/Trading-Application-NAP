package com.napgroup.models;

import jakarta.persistence.Entity;

@Entity
public class BidOrders extends OrderTableSuper {

	public BidOrders() {
		super();
	}

	@Override
	public String toString() {
		return "BidOrders [getOrderId()=" + getOrderId() + ", getStockId()=" + getStockId() + ", getSalePrice()="
				+ getSalePrice() + ", getStockAmount()=" + getStockAmount() + ", getOrderStatus()=" + getOrderStatus()
				+ ", getOrderType()=" + getOrderType() + ", getSaleType()=" + getSaleType() + ", getSaleDate()="
				+ getSaleDate() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
	
	
}
