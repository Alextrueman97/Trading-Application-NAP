package com.napgroup.models;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collections;
import java.util.Collection;

// to implement our getters and setters we'll be using lombok
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

// we implement userdetails for security
@Entity
@Table (name = "user_account")
public class UserAccount implements UserDetails {

	// here we're defining a couple of properties
	@Id
	@SequenceGenerator(
			name = "UserAccount_sequence",
			sequenceName = "UserAccount_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "UserAccount_sequence"
	)
	private Long accountId;
	@Column(name = "first_name", columnDefinition = "VARCHAR(25)")
	private String firstName;
	@Column(name = "last_name", columnDefinition = "VARCHAR(25)")
	private String lastName;
	@Column(name = "email_address", unique = true, columnDefinition = "VARCHAR(50)")
	private String emailAddress;
	@Column(name = "password", columnDefinition = "VARCHAR(25)")
	private String password;
	@Enumerated(EnumType.STRING)
	private UserAccountRole userAccountRole;

	@OneToMany(mappedBy = "accountId")
	private List<AskOrders> askOrderTable;
	@OneToMany(mappedBy = "accountId")
	private List<BidOrders> bidOrderTable;


	private Boolean locked = false; // check wether the account is locked
	private Boolean enabled =false; // false because we only want to enable the user wants the confirm their email



	public UserAccount(String firstName,
					   String lastName,
					   String emailAddress,
					   String password,
					   UserAccountRole userAccountRole
	) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.userAccountRole = userAccountRole;
		this.locked = locked;
		this.enabled = enabled;
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

	// the application user has the authority to perform certain action( access certain api endpoints)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userAccountRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	@Override
	public boolean isAccountNonExpired(){
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired(){
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		return "UserAccount [accountId=" + accountId + ", emailAddress=" + emailAddress
				+ ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + "]" + ", askOrders= " + askOrderTable.size() + ", askOrders= " + bidOrderTable.size();
	}	
	

	
}

