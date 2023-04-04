package com.napgroup.services;

import com.napgroup.models.UserAccount;

public interface UserAccountService {

	public String signUpUser(UserAccount userAccount);
	public int enableAppUser(String email);
	
}

