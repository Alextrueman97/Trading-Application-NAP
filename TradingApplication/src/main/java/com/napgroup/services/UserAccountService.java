package com.napgroup.services;

import java.util.Optional;

import com.napgroup.models.UserAccount;

public interface UserAccountService {
	
	public UserAccount login(String emailAddress, String password);
	
	public UserAccount register(UserAccount userAccount);
	
	public Optional<UserAccount> findUserByUserId(int accountId);
	
	public UserAccount updateUser(UserAccount userAccount);
	
	public int updateUserBalance(double balance, int accountId);
}

