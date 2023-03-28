package com.napgroup;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.napgroup.models.OrderTable;
import com.napgroup.repositories.OrderTableRepository;
import com.napgroup.services.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
	
	@Mock
	private OrderTableRepository orderTableRepository;
	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	@Test
	void testFindAllOrdersByUserId() {
		
		
		
	}
	
	@Test 
	void findAllUnfilledOrdersByCompanyAndRegion() {}
	
	public List<OrderTable> findAllOrdersByUserId(int userId);
	public List<OrderTable> findAllUnfilledOrdersByCompanyAndRegion(int companyId, String region);

}
