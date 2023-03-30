package com.napgroup.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.transaction.Transaction;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="new_order")
public class NewOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name  = "order_id")
	private int orderId;
	@ManyToOne
	private Stocks stockId;
	@Column(name = "sale_price")
	private double salePrice;
	@Column(name = "stock_amount")
	private int stockAmount;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@Enumerated(EnumType.STRING)
	private OrderType orderType;
	@Enumerated(EnumType.STRING)
	private SaleType saleType;
	@Column(name = "sale_date")
	private LocalDateTime saleDate;
	@ManyToOne
	private UserAccount userId;
	
	public NewOrder() {}
	
	public NewOrder(int orderId, Stocks stockId, double salePrice, int stockAmount, OrderStatus orderStatus, OrderType orderType,
			SaleType saleType, LocalDateTime saleDate) {
		super();
		this.orderId = orderId;
		this.stockId = stockId;
		this.salePrice = salePrice;
		this.stockAmount = stockAmount;
		this.orderStatus = orderStatus;
		this.orderType = orderType;
		this.saleType = saleType;
		this.saleDate = saleDate;
	}
	
	//constructor without orderId
	public NewOrder(Stocks stockId, double salePrice, int stockAmount, OrderStatus orderStatus, OrderType orderType,
			SaleType saleType, LocalDateTime saleDate) {
		super();
		this.stockId = stockId;
		this.salePrice = salePrice;
		this.stockAmount = stockAmount;
		this.orderStatus = orderStatus;
		this.orderType = orderType;
		this.saleType = saleType;
		this.saleDate = saleDate;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Stocks getStockId() {
		return stockId;
	}

	public void setStockId(Stocks stockId) {
		this.stockId = stockId;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public SaleType getSaleType() {
		return saleType;
	}

	public void setSaleType(SaleType saleType) {
		this.saleType = saleType;
	}

	public int getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}	
	
}

