package com.granacerta.modules.user.infra.persistence.repository;

import com.granacerta.modules.user.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByIdAndActiveIsTrue(UUID userId);



    Optional<UserEntity> findByEmailAndActiveIsTrue(String email);
}
