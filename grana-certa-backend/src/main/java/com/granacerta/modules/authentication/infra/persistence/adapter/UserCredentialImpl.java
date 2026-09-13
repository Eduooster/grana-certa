package com.granacerta.modules.authentication.infra.persistence.adapter;

import com.granacerta.modules.authentication.domain.entity.UserCredential;
import com.granacerta.modules.authentication.domain.repository.UserCredentialRepository;
import com.granacerta.modules.authentication.infra.persistence.mapper.UserCredentialEntityMapper;
import com.granacerta.modules.authentication.infra.persistence.repository.UserCredentialJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserCredentialImpl implements UserCredentialRepository {
    private final UserCredentialEntityMapper userCredentialEntityMapper;
    private final UserCredentialJpaRepository userCredentialJpaRepository;




    @Override
    public UserCredential save(UserCredential credential) {
        return userCredentialEntityMapper.toDomain(  userCredentialJpaRepository.save(userCredentialEntityMapper.toEntity(credential)));
    }

    @Override
    public Optional<UserCredential> findByUserId(UUID userId) {
        return userCredentialJpaRepository.findByUserId(userId).map(userCredentialEntityMapper::toDomain);
    }
}
