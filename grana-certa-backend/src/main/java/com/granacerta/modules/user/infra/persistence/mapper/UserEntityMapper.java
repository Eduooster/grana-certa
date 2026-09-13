package com.granacerta.modules.user.infra.persistence.mapper;

import com.granacerta.modules.user.domain.entity.User;
import com.granacerta.modules.user.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserEntityMapper  {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User user);
}
