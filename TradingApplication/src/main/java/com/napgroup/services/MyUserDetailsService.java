package com.napgroup.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.napgroup.models.UserData;

public interface MyUserDetailsService extends UserDetailsService {
	
	public void register(UserData user) throws UserAlreadyExistsException;
	public boolean checkIfUserExist(String username);
	

}
