package com.napgroup;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.napgroup.models.OrderTableSuper;
import com.napgroup.models.Region;
import com.napgroup.repositories.AskOrdersRepository;
import com.napgroup.repositories.BidOrdersRepository;
import com.napgroup.services.AskOrdersServiceImpl;
import com.napgroup.services.BidOrdersServiceImpl;
import com.napgroup.services.OrderTableSuperServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderTableSuperServiceTest {
	
	@Mock
	private AskOrdersRepository askOrdersRepository;
	@InjectMocks
	AskOrdersServiceImpl askOrdersServiceImpl = new AskOrdersServiceImpl();
	
	@Mock
	private BidOrdersRepository bidOrdersRepository;
	@InjectMocks 
	BidOrdersServiceImpl bidOrdersServiceImpl = new BidOrdersServiceImpl();
	
	@InjectMocks
	OrderTableSuperServiceImpl orderTableSuperServiceImpl = new OrderTableSuperServiceImpl(askOrdersServiceImpl, bidOrdersServiceImpl);
	
	
	@Test 
	void testFindTotalStocksByAccountIdAndCompanyAndRegion() {
		
		int accountId = 1;
		int companyId = 1;
		Region region = Region.LSE;
		List<OrderTableSuper> asks = new ArrayList<>();
		OrderTableSuper ask1 = new OrderTableSuper();
		ask1.setStockAmount(50);
		asks.add(ask1);
		OrderTableSuper ask2 = new OrderTableSuper();
		ask2.setStockAmount(50);
		asks.add(ask2);
		when(askOrdersRepository.findCompleteOrdersByAccountIdAndCompanyAndRegion(accountId, companyId, region)).thenReturn(asks);
		
		List<OrderTableSuper> bids = new ArrayList<>();
		OrderTableSuper bid1 = new OrderTableSuper();
		bid1.setStockAmount(50);
		bids.add(bid1);
		OrderTableSuper bid2 = new OrderTableSuper();
		bid2.setStockAmount(100);
		bids.add(bid2);
		when(bidOrdersRepository.findCompleteOrdersByAccountIdAndCompanyAndRegion(accountId, companyId, region)).thenReturn(bids);
		
		when(orderTableSuperServiceImpl.findTotalStocksByAccountIdAndCompanyAndRegion(accountId, companyId, region)).thenReturn(50);
		
		int result = orderTableSuperServiceImpl.findTotalStocksByAccountIdAndCompanyAndRegion(accountId, companyId, region);
		
		assertEquals(50, result);
	}
	
	
}
