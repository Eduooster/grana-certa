package com.granacerta.modules.authentication.domain.repository;

import com.granacerta.modules.authentication.domain.entity.UserAuth;


import java.util.Optional;

public interface AuthRepository {
    Optional<UserAuth> findByEmail(String email);
}
