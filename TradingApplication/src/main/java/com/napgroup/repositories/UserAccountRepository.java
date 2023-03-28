package com.napgroup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.napgroup.models.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

}
