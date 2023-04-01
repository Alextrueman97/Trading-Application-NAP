package com.napgroup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.napgroup.models.UserAccount;
import com.napgroup.repositories.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Override
	public UserAccount addUserAccount(UserAccount user) {
		return userAccountRepository.save(user);
	}
	
	public UserAccount findUserById(int userId) {
		return userAccountRepository.findById(userId).get();
	}

}
