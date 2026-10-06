package com.granacerta.modules.recurrence.domain.entity;

import com.granacerta.modules.recurrence.application.usecase.CreateRecurrenceCommand;
import com.granacerta.modules.recurrence.domain.enums.RecurrenceFrequency;
import com.granacerta.modules.recurrence.domain.enums.RecurrenceStatus;
import com.granacerta.modules.transaction.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Recurrence {
    private UUID id;
    private UUID userId;
    private UUID accountId;
    private UUID categoryId;

    private TransactionType type;
    private BigDecimal amount;

    private LocalDate startDate;
    private LocalDate endDate;

    private RecurrenceFrequency frequency;
    private Integer intervalValue;

    private String description;

    private RecurrenceStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate nextOccurrence;
    public static Recurrence create(CreateRecurrenceCommand command) {

        Recurrence recurrence = new Recurrence();

        recurrence.userId = command.userId();
        recurrence.accountId = command.accountId();
        recurrence.categoryId = command.categoryId();
        recurrence.type = command.type();
        recurrence.amount = command.amount();
        recurrence.startDate = command.startDate();
        recurrence.endDate = command.endDate();
        recurrence.frequency = command.frequency();
        recurrence.intervalValue = command.interval();
        recurrence.description = command.description();
        recurrence.status = RecurrenceStatus.ACTIVE;
        recurrence.createdAt = LocalDateTime.now();
        recurrence.updatedAt = LocalDateTime.now();
        recurrence.nextOccurrence = command.startDate() ;

        return recurrence;
    }

    private LocalDate calculateNextOccurrence() {

        return switch (frequency) {
            case DAILY -> startDate.plusDays(intervalValue);
            case WEEKLY -> startDate.plusWeeks(intervalValue);
            case MONTHLY -> startDate.plusMonths(intervalValue);
            case YEARLY -> startDate.plusYears(intervalValue);
        };
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }


    public LocalDate getNextOccurrence() {
        return nextOccurrence;
    }

    public void setNextOccurrence(LocalDate nextOccurrence) {
        this.nextOccurrence = nextOccurrence;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public RecurrenceFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(RecurrenceFrequency frequency) {
        this.frequency = frequency;
    }

    public Integer getIntervalValue() {
        return intervalValue;
    }

    public void setIntervalValue(Integer intervalValue) {
        this.intervalValue = intervalValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecurrenceStatus getStatus() {
        return status;
    }

    public void setStatus(RecurrenceStatus status) {
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
        Recurrence that = (Recurrence) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
