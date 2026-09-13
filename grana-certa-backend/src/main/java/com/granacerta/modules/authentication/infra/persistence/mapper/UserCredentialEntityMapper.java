package com.granacerta.modules.authentication.infra.persistence.mapper;

import com.granacerta.modules.authentication.domain.entity.UserCredential;
import com.granacerta.modules.authentication.infra.persistence.entity.UserCredentialEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCredentialEntityMapper {

    UserCredentialEntity toEntity(UserCredential userCredential);

    UserCredential toDomain(UserCredentialEntity entity);
}
