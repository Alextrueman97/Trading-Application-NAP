package com.napgroup.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ExternalBankingService {

	public HttpStatus login(String phoneNo, String pass, long accountNo);
	public HttpStatus withdraw(String phoneNo, String pass, long accountNo, double amount);
	
}
