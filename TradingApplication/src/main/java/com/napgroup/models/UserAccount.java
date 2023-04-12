package com.napgroup.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "user_account")
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Auto Increment
	@Column(name = "account_id")
	private int accountId;
	@Column(name = "username", unique = true, columnDefinition = "VARCHAR(25)")
	private String username; 	//unique
	@Column(name = "email_address", unique = true, columnDefinition = "VARCHAR(50)")
	private String emailAddress; //unique
	@Column(name = "password", columnDefinition = "VARCHAR(65)")
	private String password;
	@Column(name = "first_name", columnDefinition = "VARCHAR(25)")
	private String firstName;
	@Column(name = "last_name", columnDefinition = "VARCHAR(25)")
	private String lastName;
	@OneToMany(mappedBy = "accountId")
	private List<AskOrders> askOrderTable;
	@OneToMany(mappedBy = "accountId")
	private List<BidOrders> bidOrderTable;
	@Column(name = "balance")
	private double balance;
	
	public UserAccount() {
		super();
	}

	
	public UserAccount(int accountId, String username, String emailAddress, String password, String firstName, String lastName, double balance) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.emailAddress = emailAddress;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}
	
	public UserAccount(String emailAddress, String password) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	public UserAccount(String username, String emailAddress, String password, String firstName, String lastName, double balance) {
		super();
		this.username = username;
		this.emailAddress = emailAddress;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}
	
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

	public List<AskOrders> getAskOrderTable() {
		return askOrderTable;
	}


	public void setAskOrderTable(List<AskOrders> askOrderTable) {
		this.askOrderTable = askOrderTable;
	}


	public List<BidOrders> getBidOrderTable() {
		return bidOrderTable;
	}


	public void setBidOrderTable(List<BidOrders> bidOrderTable) {
		this.bidOrderTable = bidOrderTable;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "UserAccount [accountId=" + accountId + ", username=" + username + ", emailAddress=" + emailAddress
				+ ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + "]" + ", askOrders= " + askOrderTable.size() + ", askOrders= " + bidOrderTable.size() + "balance=" + balance;
	}	
	

	
}
