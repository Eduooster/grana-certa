package com.granacerta.modules.authentication.infra.persistence.entity;


import com.granacerta.modules.user.infra.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "grana_certa_user_credential")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String passwordHash;


    private UUID userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
