package com.granacerta.modules.user.domain.repository;

import com.granacerta.modules.user.domain.entity.User;



import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findByEmailAndActiveTrue(String email);

    Optional<User> findByIdAndActiveTrue(UUID id);

    boolean existsByEmail(String email);

    User save(User user);


}
