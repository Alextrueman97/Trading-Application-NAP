package com.napgroup.services;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.napgroup.models.UserAccount;
import com.napgroup.repositories.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	public UserAccount register(UserAccount userAccount) {
		return userAccountRepository.save(userAccount);
	}
	

	public UserAccount findUserById(int userId) {
		return userAccountRepository.findById(userId).get();
	}


	public UserAccount login(String emailAddress, String password) {
		return userAccountRepository.login(emailAddress, password);
	}


	@Override
	public UserAccount addUserAccount(UserAccount user) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
