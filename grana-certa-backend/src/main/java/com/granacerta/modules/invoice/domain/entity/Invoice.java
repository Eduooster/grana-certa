package com.granacerta.modules.invoice.domain.entity;

import com.granacerta.modules.invoice.application.usecase.CreateInvoiceCommand;
import com.granacerta.modules.invoice.domain.enums.InvoiceStatus;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Objects;
import java.util.UUID;

public class Invoice {
    private UUID id;

    private UUID accountId;

    private YearMonth referenceMonth;

    private LocalDate closingDate;
    private LocalDate openingDate;

    private LocalDate dueDate;



    private InvoiceStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static Invoice create(CreateInvoiceCommand command) {

        Invoice invoice = new Invoice();


        invoice.accountId = command.accountId();
        invoice.referenceMonth = command.referenceMonth();
        invoice.closingDate = command.closingDate();
        invoice.dueDate = command.dueDate();
        invoice.openingDate = command.openingDate();

        invoice.status = InvoiceStatus.OPEN;
        invoice.createdAt = LocalDateTime.now();
        invoice.updatedAt = LocalDateTime.now();

        return invoice;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public YearMonth getReferenceMonth() {
        return referenceMonth;
    }

    public void setReferenceMonth(YearMonth referenceMonth) {
        this.referenceMonth = referenceMonth;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }



    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
