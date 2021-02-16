package com.bdg.entity.transaction;

import com.bdg.common.enums.Status;
import com.bdg.common.enums.TransactionType;
import com.bdg.entity.bankaccount.BankAccount;
import com.bdg.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_transactoin")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long owner_id;

    private Long accountId;

    @Column(name = "transaction_amount",nullable = false)
    private double transactionAmount;

    @Column(name = "transaction_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "created_date")
    private LocalDateTime date;

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private Status status;



    public Transaction(Long accountId, LocalDateTime date, double transactionAmount, TransactionType transactionType, Long owner_id) {
        this.accountId = accountId;
        this.date = date;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.owner_id = owner_id;
    }
}
