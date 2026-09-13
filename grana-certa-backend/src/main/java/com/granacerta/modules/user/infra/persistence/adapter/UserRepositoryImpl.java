package com.granacerta.modules.user.infra.persistence.adapter;

import com.granacerta.modules.user.domain.entity.User;
import com.granacerta.modules.user.domain.repository.UserRepository;
import com.granacerta.modules.user.infra.persistence.entity.UserEntity;
import com.granacerta.modules.user.infra.persistence.mapper.UserEntityMapper;
import com.granacerta.modules.user.infra.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper  userEntityMapper;



    @Override
    public Optional<User> findByEmailAndActiveTrue(String email) {
        return userJpaRepository.findByEmailAndActiveIsTrue(email).map(userEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findByIdAndActiveTrue(UUID  id) {
        return userJpaRepository.findByIdAndActiveIsTrue(id).map(userEntityMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        UserEntity savedUserEntity = userJpaRepository.save(userEntity);
        return userEntityMapper.toDomain(savedUserEntity);
    }



}
