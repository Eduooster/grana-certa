package com.granacerta.modules.transfer.application.usecase;

import com.granacerta.modules.transfer.domain.entity.Transfer;
import com.granacerta.modules.transfer.domain.exception.TransferNotFoundException;
import com.granacerta.modules.transfer.domain.repository.TransferRepository;

public class UpdateTransferUseCase {

    private final TransferRepository transferRepository;

    public UpdateTransferUseCase(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public void execute(UpdateTransferCommand command) {

        Transfer transfer = transferRepository
                .findByIdAndUserIdAndActiveTrue(command.transferId(), command.userId())
                .orElseThrow(()->new TransferNotFoundException("Transfer not found"));

        transfer.update(command);

        transferRepository.save(transfer);
    }
}
