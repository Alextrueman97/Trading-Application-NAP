package com.napgroup.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "user_account")
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//Auto Increment
	@Column(name = "account_id")
	private int accountId;
	@Column(name = "username", unique = true, columnDefinition = "VARCHAR(25)")
	private String username; 	//unique
	@Column(name = "email_address", unique = true, columnDefinition = "VARCHAR(50)")
	private String emailAddress; //unique
	@Column(name = "password", columnDefinition = "VARCHAR(25)")
	private String password;
	@Column(name = "first_name", columnDefinition = "VARCHAR(25)")
	private String firstName;
	@Column(name = "last_name", columnDefinition = "VARCHAR(25)")
	private String lastName;
	
	public UserAccount() {
		super();
	}

	//Constructor not including accountId as it uses Auto Increment
	public UserAccount(String username, String emailAddress, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.emailAddress = emailAddress;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserAccount [accountId=" + accountId + ", username=" + username + ", emailAddress=" + emailAddress
				+ ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
}