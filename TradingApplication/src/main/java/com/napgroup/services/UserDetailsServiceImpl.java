package com.napgroup.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.napgroup.models.MyUserDetails;
import com.napgroup.models.UserAccount;
import com.napgroup.models.UserData;
import com.napgroup.repositories.UserAccountRepository;

@Service
public class UserDetailsServiceImpl implements MyUserDetailsService {

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = userAccountRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new MyUserDetails(user);
	}

	public void register(UserData user) throws UserAlreadyExistsException {

		// Let's check if user already registered with us
		if (checkIfUserExist(user.getUsername())) {
			throw new UserAlreadyExistsException("User already exists for this username");
		}
		UserAccount userEntity = new UserAccount();
		BeanUtils.copyProperties(user, userEntity);
		encodePassword(userEntity, user);
		userAccountRepository.save(userEntity);
	}

	public boolean checkIfUserExist(String username) {
		return userAccountRepository.findByUsername(username) != null ? true : false;
	}

	private void encodePassword(UserAccount userEntity, UserData user) {
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
	}

}
