package com.granacerta.modules.financialProfile.infra.persistence.mapper;

import com.granacerta.modules.financialProfile.domain.entity.FinancialProfile;
import com.granacerta.modules.financialProfile.infra.persistence.entity.FinancialProfileEntity;
import com.granacerta.modules.user.domain.entity.User;
import com.granacerta.modules.user.infra.persistence.mapper.UserEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FinancialProfileEntityMapper {



    FinancialProfileEntity toEntity(FinancialProfile financialProfile);

    FinancialProfile toDomain(FinancialProfileEntity entity);



}
