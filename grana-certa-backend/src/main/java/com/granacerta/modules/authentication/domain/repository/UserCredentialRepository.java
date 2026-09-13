package com.granacerta.modules.authentication.domain.repository;

import com.granacerta.modules.authentication.domain.entity.UserCredential;

import java.util.Optional;
import java.util.UUID;

public interface UserCredentialRepository {


    UserCredential save(UserCredential credential);

    Optional<UserCredential> findByUserId(UUID id);
}
