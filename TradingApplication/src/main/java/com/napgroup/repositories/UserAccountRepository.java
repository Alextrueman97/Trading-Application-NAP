package com.napgroup.repositories;



import com.napgroup.models.UserAccount;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

        import java.util.Optional;

@Repository
@Transactional(readOnly =true)
@Component

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    //this code writes our sql query

    Optional<UserAccount> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserAccount a" + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}