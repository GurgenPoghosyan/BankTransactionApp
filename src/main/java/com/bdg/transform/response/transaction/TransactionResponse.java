package com.bdg.transform.response.transaction;

import com.bdg.common.enums.Status;
import com.bdg.common.enums.TransactionType;
import com.bdg.entity.bankaccount.BankAccount;
import com.bdg.entity.user.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TransactionResponse {

    private Long id;

    private Long accountId;

    private LocalDateTime date;

    private double transactionAmount;

    private TransactionType transactionType;

    private Status status;

    private Long user_id;
}
