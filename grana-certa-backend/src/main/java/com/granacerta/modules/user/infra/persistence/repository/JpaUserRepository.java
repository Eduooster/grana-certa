package com.granacerta.modules.user.infra.persistence.repository;

import com.granacerta.modules.user.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
