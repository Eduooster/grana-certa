package com.granacerta.modules.transfer.infra.persistence.mapper;

import com.granacerta.modules.transfer.domain.entity.Transfer;
import com.granacerta.modules.transfer.infra.persistence.entity.TransferEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TranferMapper {

    Transfer toDomain(TransferEntity entity);
    TransferEntity toEntity(Transfer transfer);
}
