package com.napgroup.models;


import com.napgroup.models.UserAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class  ConfirmationToken {

    @Id
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "UserAccount_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private UserAccount userAccount;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt,  UserAccount userAccount) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiredAt;
        this.userAccount = userAccount;
    }
}
