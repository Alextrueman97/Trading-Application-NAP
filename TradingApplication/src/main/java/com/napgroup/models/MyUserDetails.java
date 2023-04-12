package com.napgroup.models;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private UserAccount userAccount;
	
	public MyUserDetails(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return userAccount.getPassword();
	}

	@Override
	public String getUsername() {
		return userAccount.getUsername();
	}
	
	public int getAccountId() {
		return userAccount.getAccountId();
	}

	public String getEmailAddress() {
		return userAccount.getEmailAddress();
	}
	
	public double getBalance() {
		return userAccount.getBalance();
	}

	public String getFirstName() {
		return userAccount.getFirstName();
	}
	
	public String getLastName() {
		return userAccount.getLastName();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
