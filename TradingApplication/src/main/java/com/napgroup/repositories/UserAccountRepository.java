package com.napgroup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.napgroup.models.UserAccount;

import jakarta.transaction.Transactional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	@Query(value = "select new UserAccount(ua.accountId, ua.username, ua.emailAddress, ua.password, ua.firstName, ua.lastName, ua.balance) from UserAccount ua where ua.username = :username")
	public UserAccount findByUsername(@Param("username") String username);
	
	@Query(value = "select new UserAccount(ua.accountId, ua.username, ua.emailAddress, ua.password, ua.firstName, ua.lastName, ua.balance) from UserAccount ua where ua.emailAddress = :emailAddress")
	public UserAccount findByEmailAddress(@Param("emailAddress") String emailAddress);
	
	@Query(value = "select new UserAccount(ua.accountId, ua.username, ua.emailAddress, ua.password, ua.firstName, ua.lastName, ua.balance) from UserAccount ua where ua.emailAddress = :emailAddress and ua.password = :password")
	public UserAccount login(@Param("emailAddress" )String emailAddress, @Param("password") String password);;

	@Query(value = "update UserAccount ua set ua.balance = :balance where ua.accountId = :accountId")
	@Transactional
	@Modifying(clearAutomatically = true)
	public int updateUserBalance(@Param("balance") double balance, @Param("accountId") int accountId);
	
}
