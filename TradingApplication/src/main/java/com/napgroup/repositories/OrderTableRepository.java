package com.napgroup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.napgroup.models.OrderTable;

public interface OrderTableRepository extends JpaRepository<OrderTable, Integer>{

}
