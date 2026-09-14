package com.granacerta.modules.transaction.infra.persistence.entity;



import com.granacerta.modules.transaction.domain.enums.TransactionSource;
import com.granacerta.modules.transaction.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(
        name = "GRANA_CERTA_TRANSACTIONS",
        indexes = {
                @Index(name = "idx_transaction_user_id", columnList = "user_id"),
                @Index(name = "idx_transaction_account_id", columnList = "account_id"),
                @Index(name = "idx_transaction_category_id", columnList = "category_id"),
                @Index(name = "idx_transaction_invoice_id", columnList = "invoice_id"),
                @Index(name = "idx_transaction_transfer_id", columnList = "transfer_id"),
                @Index(name = "idx_transaction_recurrence_id", columnList = "recurrence_id")
        }
)
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "invoice_id")
    private UUID invoiceId;

    @Column(name = "transfer_id")
    private UUID transferId;

    @Column(name = "recurrence_id")
    private UUID recurrenceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 30)
    private TransactionType type;

    @Column(name = "amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "source", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TransactionSource source;

    @Column(name = "external_id", length = 255)
    private String externalId;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    private boolean active;




}