package com.napgroup.services;


import com.napgroup.models.ConfirmationToken;
import com.napgroup.models.UserAccount;
import com.napgroup.repositories.UserAccountRepository;
import com.napgroup.services.ConfirmationTokenServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

// this class implements a class specific for spring security
// this is actually how we find users once we try to login

// getters and setters implemented using lombok
@Service
@AllArgsConstructor

public class UserAccountServiceImpl implements UserDetailsService, UserAccountService {
	private final static String USER_NOT_FOUND_MSG = " user with email %s not found";

	private final UserAccountRepository userAccountRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenServiceImpl confirmationTokenServiceImpl;


	// find user through the username(email)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return (UserDetails) userAccountRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)) );
	}

	public String signUpUser(UserAccount userAccount) {
		//1) check whether the user exists
		boolean userExists = userAccountRepository.findByEmail(userAccount.getEmailAddress())
				.isPresent();

		if(userExists) {
			//TODO if the same user is trying to register and they havent confirmed their email yet send anthother otherwise we throw the exception
			// TODO if email not confirmed send confirmation email

			throw new IllegalStateException("email already taken");
		}

		String encodedPassword = bCryptPasswordEncoder.encode(userAccount.getPassword());

		userAccount.setPassword(encodedPassword);
// then we need to save the user
		userAccountRepository.save(userAccount);

		String token = UUID.randomUUID().toString();
		//Send confirmation token, we create a token and then save it
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), userAccount);

		confirmationTokenServiceImpl.saveConfirmationToken(confirmationToken);

		//TODO: SEND EMAIL
		return token;
	}

	public int enableAppUser(String email) {
		return userAccountRepository.enableAppUser(email);
	}
}
