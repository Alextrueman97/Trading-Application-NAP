package com.napgroup.services;

import com.napgroup.models.Stocks;

public interface StockService {
	
	public Stocks findStockById(int stockId);
	public Stocks addStock(Stocks stock);
	public int updateStockAmountById(int stockId, int stockAmount);

}
