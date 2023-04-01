package com.napgroup.services;

import com.napgroup.models.UserAccount;

public interface UserAccountService {
		
	public UserAccount addUserAccount(UserAccount user);
	public UserAccount findUserById(int userId);
	
}

