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
//	private final PasswordEncoder passwordEncoder;
//
//	public UserAccountServiceImpl(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
//		this.userAccountRepository = userAccountRepository;
//		this.passwordEncoder = passwordEncoder;
//	}
//	
//	@Override
//	public UserAccount register(UserAccount user) {
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
//		return userAccountRepository.save(user);
//	}
//	
//	@Override
//	public UserAccount login(String username, String password) {
//		UserAccount user = userAccountRepository.findByUsername(username);
//		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//			return user;
//		}
//		return null;
//	}
	
	public UserAccount register(UserAccount userAccount) {
		return userAccountRepository.save(userAccount);
	}
	
	public UserAccount login(String emailAddress, String password) {
		return userAccountRepository.login(emailAddress, password);
	}
	
}
