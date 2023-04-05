package com.napgroup.services;

import com.napgroup.models.UserAccount;

public interface UserAccountService {
	
//	UserAccount register(UserAccount user);
//	
//	UserAccount login(String username, String password);
	
	public UserAccount login(String emailAddress, String password);
	
	public UserAccount register(UserAccount userAccount);
}

