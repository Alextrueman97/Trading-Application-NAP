package com.napgroup.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

public class OrderTableSuperComparator implements Comparator<OrderTableSuper> {

	@Override
	public int compare(OrderTableSuper o1, OrderTableSuper o2) {
		
		LocalDate o1Date = o1.getSaleDate().toLocalDate();
		LocalDate o2Date = o2.getSaleDate().toLocalDate();
		if(o1Date.isEqual(o2Date)) {
			LocalTime o1Time = o1.getSaleDate().toLocalTime();
			LocalTime o2Time = o1.getSaleDate().toLocalTime();
			return o1Time.compareTo(o2Time);
		}
		System.out.println(o1Date + " compared to " + o2Date + ": " + o1Date.compareTo(o2Date));
		return o1Date.compareTo(o2Date);
	}

}
