package com.granacerta.modules.authentication.infra.persistence.repository;

import com.granacerta.modules.authentication.infra.persistence.entity.UserCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCredentialJpaRepository extends JpaRepository<UserCredentialEntity, Long> {

    Optional<UserCredentialEntity> findByUserId(UUID userId);
}
