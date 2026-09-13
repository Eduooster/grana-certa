package com.granacerta.modules.financialAccount.infra.persistence.mapper;


import com.granacerta.modules.financialAccount.domain.entity.FinancialAccount;
import com.granacerta.modules.financialAccount.infra.persistence.entity.FinancialAccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinancialAccountMapper {

    FinancialAccount toDomain(FinancialAccountEntity entity);
    FinancialAccountEntity toEntity(FinancialAccount financialAccount);

}
