package com.granacerta.modules.financialProfile.infra.persistence.mapper;

import com.granacerta.modules.financialProfile.domain.entity.FinancialProfile;
import com.granacerta.modules.financialProfile.infra.persistence.entity.FinancialProfileEntity;
import com.granacerta.modules.user.domain.entity.User;
import com.granacerta.modules.user.infra.persistence.mapper.UserEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FinancialProfileEntityMapper {


     @Mapping(target = "userId", source = "user.id")
    FinancialProfileEntity toEntity(FinancialProfile financialProfile);

    @Mapping(target = "user", ignore = true)
    FinancialProfile toDomain(FinancialProfileEntity entity);

    default FinancialProfile toDomain(
            FinancialProfileEntity entity,
            User user
    ) {
        FinancialProfile financialProfile = toDomain(entity);
        financialProfile.setUser(user);
        return financialProfile;
    }


}
