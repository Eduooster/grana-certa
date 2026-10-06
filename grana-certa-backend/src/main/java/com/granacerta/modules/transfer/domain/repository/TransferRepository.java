package com.granacerta.modules.transfer.domain.repository;

import com.granacerta.modules.transfer.domain.entity.Transfer;


import java.util.Optional;
import java.util.UUID;

public interface TransferRepository {
    Transfer save(Transfer transfer);

    Optional<Transfer> findByIdAndUserIdAndActiveTrue(UUID tranferId, UUID userId);
}
