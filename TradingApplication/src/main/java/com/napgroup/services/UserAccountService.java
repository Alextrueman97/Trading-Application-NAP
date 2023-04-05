package com.napgroup.services;

import com.napgroup.models.UserAccount;

public interface UserAccountService {
<<<<<<< HEAD
		
	public UserAccount addUserAccount(UserAccount user);
	public UserAccount findUserById(int userId);
=======
>>>>>>> front-end
	
//	UserAccount register(UserAccount user);
//	
//	UserAccount login(String username, String password);
	
	public UserAccount login(String emailAddress, String password);
	
	public UserAccount register(UserAccount userAccount);
}

