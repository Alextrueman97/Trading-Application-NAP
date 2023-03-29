package com.napgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.napgroup.models.Region;
import com.napgroup.models.Stocks;
import com.napgroup.repositories.StocksRepository;
import com.napgroup.services.StockServiceImpl;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {
	
	@Mock
	private StocksRepository stocksRepository;
	@InjectMocks
	private StockServiceImpl stockServiceImpl;

	@Test
	void testUpdateStockAmountById() {
		
		Stocks stock = new Stocks(300.0, Region.LSE, 10);
		when(stockServiceImpl.updateStockAmountById(1, 200)).thenReturn(stock);
		
		Stocks updatedStock = stockServiceImpl.updateStockAmountById(1, 200);
		assertEquals(updatedStock, stock);
		
	}

}
