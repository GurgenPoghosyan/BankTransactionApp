package com.bdg.transform.request.trannsaction;

import com.bdg.common.enums.TransactionType;
import com.bdg.entity.user.User;
import lombok.Data;

@Data
public class TransactionRequest {

    private Long accountId;

    private double transactionAmount;

    private TransactionType transactionType;

    private Long user_id;
}
