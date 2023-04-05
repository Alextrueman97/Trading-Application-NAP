package com.napgroup.services;

import com.napgroup.models.UserAccount;

public interface UserAccountService {

		
	public UserAccount addUserAccount(UserAccount user);
	public UserAccount findUserById(int userId);

	

	
	public UserAccount login(String emailAddress, String password);
	
	public UserAccount register(UserAccount userAccount);
}

