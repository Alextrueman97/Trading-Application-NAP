package com.napgroup.services;

public class UserAlreadyExistsException extends Exception {
	
	public UserAlreadyExistsException(String msg) {
		super(msg);
	}

}
