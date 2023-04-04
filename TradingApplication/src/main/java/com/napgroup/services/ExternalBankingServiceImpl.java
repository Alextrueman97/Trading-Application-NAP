package com.napgroup.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalBankingServiceImpl implements ExternalBankingService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public HttpStatus login(String phoneNo, String pass, long accountNo) {
		String url = "http://localhost:8082/api/external-banking";
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		JSONObject loginObject = new JSONObject();
		loginObject.put("phoneNo", phoneNo);
		loginObject.put("password", pass);
		loginObject.put("accountNo", accountNo);
		HttpEntity<String> request = new HttpEntity<String>(loginObject.toString(), header);
		HttpStatus status = restTemplate.postForObject(url, request, HttpStatus.class);
		return status;
	}

	@Override
	public HttpStatus withdraw(String phoneNo, String pass, long accountNo, double amount) {
		String url = "http://localhost:8082/api/external-banking/withdraw";
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		JSONObject loginObject = new JSONObject();
		loginObject.put("phoneNo", phoneNo);
		loginObject.put("password", pass);
		loginObject.put("accountNo", accountNo);
		loginObject.put("withdrawAmount", amount);
		HttpEntity<String> request = new HttpEntity<String>(loginObject.toString(), header);
		HttpStatus status = restTemplate.postForObject(url, request, HttpStatus.class);
		return status;
	}
	
	
	

}
